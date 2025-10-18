package cabko.security

import common.identity.UserID
import core.users.entity.UserRole

interface JwtTokenProvider {
    fun createToken(id: UserID, username: String, roles: Set<UserRole>): String
    fun validateToken(token: String)
    fun getAuthUser(token: String): UserID
    fun getEmail(token: String): String
    fun getRoles(token: String): Set<UserRole>
}