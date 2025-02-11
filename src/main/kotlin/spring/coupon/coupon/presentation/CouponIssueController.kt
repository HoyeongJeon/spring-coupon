package spring.coupon.coupon.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.coupon.core.presentation.RestResponse
import spring.coupon.coupon.application.CouponService
import spring.coupon.coupon.presentation.dto.request.CouponIssueRequest
import spring.coupon.coupon.presentation.dto.response.CouponIssueResponse

@RestController
@RequestMapping("/api/v1/coupon")
class CouponIssueController(
    private val couponService: CouponService
) {

    @PostMapping("/issue")
    fun issueCoupon(@RequestBody couponIssueRequest: CouponIssueRequest): ResponseEntity<RestResponse<CouponIssueResponse>> {
        return ResponseEntity.ok(RestResponse(couponService.issueCoupon(couponIssueRequest), true))
    }
}
