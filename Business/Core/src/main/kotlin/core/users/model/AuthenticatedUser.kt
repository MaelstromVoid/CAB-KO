package core.users.model

import common.identity.UserID
import core.users.entity.UserRole

interface AuthenticatedUser {
    val id: UserID
    val email: String
    val userRoles: Set<UserRole>

    fun hasRole(userRoleRequire: UserRole): Boolean = userRoles.contains(userRoleRequire)
}