package cabko.usecase.users

import cabko.repository.UserRepository
import cabko.technical.EmailAlreadyUsed
import cabko.technical.UseCase
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
    fun execute(email: String, rawPassword: String): User {
        if (userRepository.findByEmail(email) != null) {
            throw EmailAlreadyUsed()
        }
        val user = User(
            UserID(
                UUID.randomUUID()
            ),
            email,
            passwordEncoder.encode(rawPassword)!!,
            setOf(UserRole.CLIENT)
        )
        return userRepository.save(user)
    }
}