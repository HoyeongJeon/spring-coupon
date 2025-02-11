package spring.coupon.core.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import spring.coupon.core.exception.error.DomainException
import spring.coupon.core.presentation.ErrorResponse

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(DomainException::class)
    fun handleDomainException(ex: DomainException): ResponseEntity<ErrorResponse> {
        val httpStatus = ex.httpStatus
        val errorResponse = ErrorResponse.createDomainErrorResponse(httpStatus.value(), ex)
        return ResponseEntity.status(httpStatus).body(errorResponse)
    }
}
