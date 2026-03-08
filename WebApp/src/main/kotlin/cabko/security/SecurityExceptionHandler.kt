package cabko.security

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler


/**
 * Handles security exceptions that occur BEFORE a request reaches a controller.
 *
 * In Spring Security, several errors can happen during the filter chain:
 *
 * - Authentication failures (invalid or missing JWT)
 * - Access denied errors (user authenticated but lacking required roles)
 * - Other security related exceptions thrown by filters
 *
 * These errors occur BEFORE the request reaches a @Controller, meaning that
 * a @ControllerAdvice cannot intercept them.
 *
 * Why is this important?
 *
 * Default Spring Security responses may expose implementation details
 * (stack traces, exception names, framework messages, etc.). Exposing such
 * information can help an attacker understand the internal structure of
 * the application and craft more precise attacks.
 *
 * This handler enforces a consistent and minimal response strategy:
 *
 * - Always return a sanitized JSON error response
 * - Log the real exception internally for debugging
 * - Avoid leaking security or infrastructure details
 *
 * Result:
 * Attackers receive minimal information, while developers still get
 * complete logs for troubleshooting.
 */
class SecurityExceptionHandler(
    private val log: KLogger = KotlinLogging.logger {}
) : AccessDeniedHandler, AuthenticationEntryPoint {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        handleSecurityException(request, response, accessDeniedException)
    }

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        handleSecurityException(request, response, authException)
    }

    private fun handleSecurityException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: RuntimeException
    ) {
        // Log the real error internally for debugging / monitoring
        log.error(exception) {
            "Security exception intercepted : ${request.method} for ${request.requestURI}"
        }

        // Build a generic response to avoid leaking security details
        response.status = HttpServletResponse.SC_BAD_REQUEST
        response.contentType = "application/json"

        val body = """
        {
            "status": 400,
            "error": "Bad Request",
            "message": "The request could not be processed"
        }
        """.trimIndent()

        response.writer.write(body)
    }
}