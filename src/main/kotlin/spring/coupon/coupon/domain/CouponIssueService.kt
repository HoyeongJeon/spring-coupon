package spring.coupon.coupon.domain

import org.springframework.stereotype.Service
import spring.coupon.coupon.exception.CouponErrorCode

@Service
class CouponIssueService(
    private val couponRepository: CouponRepository,
) {
    fun issueCoupon(userId: Long): Coupon? {
        couponRepository.findByUserId(userId)?.let {
            return it
        }

        val couponWithIdNull = couponRepository.findFirstByUserIdIsNullOrderByIdAsc()
            ?: throw CouponErrorCode.ALL_COUPON_ISSUED.toException()

        couponWithIdNull.userId = userId

        return couponWithIdNull
    }
}
