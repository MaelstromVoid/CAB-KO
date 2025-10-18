package common.error

import common.identity.ProductID

// 400


// 401
class PasswordError() : UnauthorizedError("Password wrong")

// 403
class NotAuthorized(reason: String) : ForbiddenError("Not authorized: $reason")

// 404
class UserNotFound(username: String) : NotFoundError("User '$username' not found")
class ProductNotFound(productID: ProductID) : NotFoundError("Product with ID ${productID.id} not found")

// 409
class UserAlreadyExists(username: String) : ConflitError("User '$username' already exists")

// 500

