package spring.coupon.coupon.infrastructure.configuration

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import spring.coupon.coupon.domain.Coupon
import spring.coupon.coupon.domain.CouponRepository

@Configuration
class CouponInitializer(
    private val couponRepository: CouponRepository
) {
    @PostConstruct
    fun initCoupon() {
        for (i in 1..100) {
            couponRepository.save(Coupon(code = CouponIdGenerator.codeGenerate()))
        }
    }
}
