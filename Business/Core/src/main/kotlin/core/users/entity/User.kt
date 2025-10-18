package core.users.entity

import common.Entity
import common.identity.UserID

data class User(
    val userID: UserID,
    val email: String,
    val password: String,
    val userRoles: Set<UserRole>
) : Entity