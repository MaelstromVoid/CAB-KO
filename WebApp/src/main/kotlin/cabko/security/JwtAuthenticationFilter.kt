package cabko.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")
        if (header != null && header.startsWith("Bearer ")) {
            val token = header.substring(7)
            try {
                jwtTokenProvider.validateToken(token)
                val userRoles = jwtTokenProvider.getRoles(token)
                val auth = JwtUserAuthentication(
                    id = jwtTokenProvider.getAuthUser(token),
                    email = jwtTokenProvider.getEmail(token),
                    userRoles = userRoles,
                    authorities = userRoles.map { SimpleGrantedAuthority("ROLE_$it") },
                )
                SecurityContextHolder.getContext().authentication = auth
            } catch (_: Exception) {
                SecurityContextHolder.clearContext()
            }
        }
        filterChain.doFilter(request, response)
    }
}
