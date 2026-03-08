package common.error

// 400


// 401

// 403
class NotAuthorized(reason: String) : ForbiddenError("Not authorized: $reason")

// 404

// 409

// 500

