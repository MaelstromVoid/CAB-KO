package cabko

import cabko.common.ErrorResponseDTO
import common.error.ApplicationError
import common.error.BadRequestError
import common.error.ConflictError
import common.error.ForbiddenError
import common.error.NotFoundError
import common.error.UnauthorizedError
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.ServletException
import org.springframework.core.NestedRuntimeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tools.jackson.core.JacksonException

@RestControllerAdvice
class GlobalExceptionHandler(
    private val log: KLogger = KotlinLogging.logger {},
) {
    @ExceptionHandler(ApplicationError::class)
    fun handleApplicationError(exception: ApplicationError): ResponseEntity<ErrorResponseDTO> {
        log.error { exception.stackTraceToString() }
        val status = when (exception) {
            is BadRequestError -> HttpStatus.BAD_REQUEST
            is UnauthorizedError -> HttpStatus.UNAUTHORIZED
            is ForbiddenError -> HttpStatus.FORBIDDEN
            is NotFoundError -> HttpStatus.NOT_FOUND
            is ConflictError -> HttpStatus.CONFLICT
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        val errorResp = ErrorResponseDTO(
            status = status.value(),
            error = status.reasonPhrase,
            message = exception.message
        )
        return ResponseEntity(errorResp, status)
    }

    @ExceptionHandler(JacksonException::class, ServletException::class, NestedRuntimeException::class)
    fun handleSpringError(exception: Exception): ResponseEntity<ErrorResponseDTO> {
        log.error { exception.stackTraceToString() }
        val status = HttpStatus.BAD_REQUEST
        val errorResp = ErrorResponseDTO(
            status = status.value(),
            error = status.reasonPhrase,
            message = "The request could not be processed"
        )
        return ResponseEntity(errorResp, status)
    }

    @ExceptionHandler(Exception::class)
    fun handleOtherError(exception: Exception): ResponseEntity<ErrorResponseDTO> {
        log.error { exception.stackTraceToString() }
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val errorResp = ErrorResponseDTO(
            status = status.value(),
            error = status.reasonPhrase,
            message = "An unexpected error occurred"
        )
        return ResponseEntity(errorResp, status)
    }
}