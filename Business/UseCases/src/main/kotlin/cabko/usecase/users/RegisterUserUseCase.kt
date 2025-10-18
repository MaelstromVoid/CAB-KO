package cabko.usecase.users

import cabko.repository.UserRepository
import cabko.technical.UseCase
import common.error.UserAlreadyExists
import common.identity.UserID
import core.users.entity.User
import core.users.entity.UserRole
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

@UseCase
class RegisterUserUseCase(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun execute(username: String, rawPassword: String): User {
        if (userRepository.findByUsername(username) != null) {
            throw UserAlreadyExists(username)
        }
        val user = User(
            UserID(
                UUID.randomUUID()
            ),
            username,
            passwordEncoder.encode(rawPassword),
            setOf(UserRole.CLIENT)
        )
        return userRepository.save(user)
    }
}