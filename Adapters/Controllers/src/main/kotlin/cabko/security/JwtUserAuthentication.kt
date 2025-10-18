package cabko.security

import common.identity.UserID
import core.users.entity.UserRole
import core.users.model.AuthenticatedUser
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtUserAuthentication(
    override val id: UserID,
    override val email: String,
    override val userRoles: Set<UserRole>,

    authorities: Collection<GrantedAuthority>,
)  : AuthenticatedUser, AbstractAuthenticationToken(authorities) {

    init {
        super.setAuthenticated(true)
    }

    override fun getPrincipal(): Any = this
    override fun getCredentials(): Any? = null

    override fun setAuthenticated(isAuthenticated: Boolean) {
        if (!isAuthenticated) {
            super.setAuthenticated(false)
        } else {
            throw IllegalArgumentException("Cannot set this token to authenticated manually")
        }
    }

    override fun getName(): String = email
}
