package common.error

// 400


// 401

// 403

// 404

// 409

// 500
class WhenOutForRange(val reason: String? = null) : InternalServerError(reason ?: "")