package spring.coupon.coupon.application

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import spring.coupon.coupon.domain.CouponIssueService
import spring.coupon.coupon.presentation.dto.request.CouponIssueRequest
import spring.coupon.coupon.presentation.dto.response.CouponIssueResponse

@Service
class CouponService(
    private val couponIssueService: CouponIssueService
) {
    @Transactional
    fun issueCoupon(couponIssueRequest: CouponIssueRequest): CouponIssueResponse {
        return CouponIssueResponse(couponIssueService.issueCoupon(couponIssueRequest.userId).id!!)
    }
}
