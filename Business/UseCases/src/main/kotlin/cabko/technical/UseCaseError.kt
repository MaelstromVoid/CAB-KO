package cabko.technical

import common.error.ConflictError
import common.error.InternalServerError
import common.error.NotFoundError
import common.error.UnauthorizedError
import common.identity.ProductID

// 400


// 401
class PasswordError() : UnauthorizedError("Password wrong")

// 403

// 404
class UserNotFound(username: String) : NotFoundError("User '$username' not found")
class ProductNotFound(productID: ProductID) : NotFoundError("Product with ID ${productID.id} not found")

// 409
class EmailAlreadyUsed : ConflictError("Email is already in use")

// 500
class TransactionException() :
    InternalServerError("Transaction failed")


