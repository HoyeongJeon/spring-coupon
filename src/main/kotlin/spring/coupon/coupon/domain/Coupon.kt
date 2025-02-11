package spring.coupon.coupon.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import spring.coupon.coupon.infrastructure.CouponId

@Entity
class Coupon (
    @Column(unique = true, nullable = false)
    val userId: Long
) {
    @Id
    @CouponId
    val id: String? = null

    companion object{
        fun create(userId: Long): Coupon {
            return  Coupon(userId)
        }
    }
}
