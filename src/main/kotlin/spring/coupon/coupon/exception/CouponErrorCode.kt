package spring.coupon.coupon.exception

import org.springframework.http.HttpStatus
import spring.coupon.core.exception.error.BaseErrorCode
import spring.coupon.core.exception.error.DomainException

enum class CouponErrorCode(
    override val httpStatus: HttpStatus,
    override val message: String
) : BaseErrorCode<DomainException> {

    ALL_COUPON_ISSUED(HttpStatus.NOT_FOUND, "모든 쿠폰이 발급되었습니다.");

    override fun toException(): DomainException {
        return DomainException(httpStatus, this)
    }
}
