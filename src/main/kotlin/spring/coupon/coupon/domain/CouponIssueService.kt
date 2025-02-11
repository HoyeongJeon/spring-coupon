package spring.coupon.coupon.domain

import jakarta.transaction.Transactional
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import spring.coupon.core.exception.error.GlobalErrorCode
import spring.coupon.coupon.exception.CouponErrorCode
import java.util.concurrent.TimeUnit

@Service
class CouponIssueService(
    private val couponRepository: CouponRepository,
    private val couponCountRepository: CouponCountRepository,
    private val redissonClient: RedissonClient,
) {
    @Transactional
    fun issueCoupon(userId: Long): Coupon? {

        val lock = redissonClient.getLock("COUPON_ISSUE_DISTRIBUTED_LOCK")

        if (!lock.tryLock(2, 15, TimeUnit.SECONDS)) {
            throw GlobalErrorCode.FAILED_TO_ACQUIRE_LOCK.toException()
        }

        couponRepository.findByUserId(userId)?.let {
            return it
        }

        val couponCount = couponCountRepository.count() ?: 0

        if (couponCount >= 100) {
            throw CouponErrorCode.ALL_COUPON_ISSUED.toException()
        }

        couponCountRepository.increment()
        lock.unlock()

        return couponRepository.save(Coupon.create(userId))
    }
}
