package cabko.technical

import common.error.InternalServerError

// 400


// 401

// 403

// 404

// 409

// 500
class DataBaseLogicException() :
    InternalServerError("Unexpected database logic error encountered, this situation should not occur under normal conditions")


