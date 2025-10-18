package common.error

/**
 * Base exception for all application-level errors.
 *
 * <p>This sealed class acts as the root of the application's error hierarchy.
 * All domain, use-case and infrastructure errors that should be exposed (or
 * translated) by the application's error handling layer MUST inherit from
 * this type.</p>
 *
 * <p>Being sealed ensures that the set of direct subclasses is known at
 * compile-time and can be exhaustively handled by the application's
 * exception-to-HTTP mapping (for example, in a global {@code @ControllerAdvice}).</p>
 *
 * <p><strong>Guidelines:</strong>
 * <ul>
 *   <li>Subclasses should carry a clear, user-friendly message (avoid leaking sensitive details).</li>
 *   <li>Use specific subclasses below (e.g. {@link BadRequestError}) so the global
 *       exception handler can map them to the correct HTTP status code.</li>
 * </ul>
 * </p>
 *
 * @param message Human-readable description of the error (for logs / client responses).
 */
sealed class ApplicationError(message: String) : RuntimeException(message)


/**
 * Represents client side errors, the request cannot be processed because it is invalid.
 *
 * <p>Typical usage: validation failures, malformed requests, missing required fields,
 * or any other condition that should be reported with HTTP 400 Bad Request.</p>
 *
 * <p>The global exception handler should translate instances of this class (and its
 * subclasses) to HTTP status <strong>400 Bad Request</strong>.</p>
 */
open class BadRequestError(message: String) : ApplicationError(message) // 400


/**
 * Represents authentication failures, the request lacks valid authentication credentials.
 *
 * <p>Typical usage: login failure, missing/invalid credentials, expired token where the
 * client must re-authenticate.</p>
 *
 * <p>The global exception handler should translate instances of this class (and its
 * subclasses) to HTTP status <strong>401 Unauthorized</strong>.</p>
 */
open class UnauthorizedError(message: String) : ApplicationError(message) // 401


/**
 * Represents authorization failures, the authenticated principal does not have the required rights.
 *
 * <p>Typical usage: access to a resource is forbidden due to insufficient permissions or roles.</p>
 *
 * <p>The global exception handler should translate instances of this class (and its
 * subclasses) to HTTP status <strong>403 Forbidden</strong>.</p>
 */
open class ForbiddenError(message: String) : ApplicationError(message) // 403


/**
 * Represents resource-not-found errors.
 *
 * <p>Typical usage: entity lookup by id returned no result (e.g. user/product not found).</p>
 *
 * <p>The global exception handler should translate instances of this class (and its
 * subclasses) to HTTP status <strong>404 Not Found</strong>.</p>
 */
open class NotFoundError(message: String) : ApplicationError(message) // 404


/**
 * Represents conflict errors when a request cannot be completed due to a conflict with current state.
 *
 * <p>Typical usage: attempts to create a resource that already exists, concurrent-modification
 * conflicts, uniqueness constraint violations that are semantic rather than syntactic.</p>
 *
 * <p>The global exception handler should translate instances of this class (and its
 * subclasses) to HTTP status <strong>409 Conflict</strong>.</p>
 */
open class ConflitError(message: String) : ApplicationError(message) // 409


/**
 * Represents unexpected server-side errors.
 *
 * <p>Typical usage: unhandled exceptions, invariant violations, or unexpected conditions
 * that should be reported as HTTP 500 Internal Server Error.</p>
 *
 * <p>The global exception handler should translate instances of this class (and its
 * subclasses) to HTTP status <strong>500 Internal Server Error</strong>. Messages returned
 * to clients should remain generic; detailed diagnostics must be logged server-side only.</p>
 */
open class InternalServerError(message: String) : ApplicationError(message) // 500
