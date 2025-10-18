package cabko.security

import common.identity.UserID
import core.users.entity.UserRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date
import java.util.UUID
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenProviderImpl(
    @Value($$"${jwt.secret}") private val secret: String
) : JwtTokenProvider {

    private lateinit var key: SecretKey

    @PostConstruct
    fun init() {
        val keyBytes = Decoders.BASE64.decode(secret)
        key = SecretKeySpec(keyBytes, "HmacSHA256")
    }

    override fun createToken(id: UserID, username: String, roles: Set<UserRole>): String {
        val now = Date()
        val expiry = Date(now.time + 3600000) // 1 hour expiration

        return Jwts.builder()
            .subject(username)
            .claim("id", id.id)
            .claim("roles", roles)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(key)
            .compact()
    }

    override fun validateToken(token: String) {
        Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
    }

    override fun getAuthUser(token: String): UserID {
        val claim: Claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
        return UserID(
            UUID.fromString(claim["id"] as String)
        )
    }

    override fun getEmail(token: String): String = Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .payload
        .subject


    override fun getRoles(token: String): Set<UserRole> {
        val payload =
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload["roles"]


        return when (payload) {
            is Collection<*> -> {
                payload.mapNotNull {
                    (it as? String)?.let { str -> UserRole.valueOf(str) }
                }.toSet()
            }

            else -> throw IllegalArgumentException("Unsupported roles claim type")
        }
    }
}
