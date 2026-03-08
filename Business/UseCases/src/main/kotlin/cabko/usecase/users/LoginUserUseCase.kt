package cabko.usecase.users

import cabko.repository.UserRepository
import cabko.technical.PasswordError
import cabko.technical.UseCase
import cabko.technical.UserNotFound
import core.users.entity.User
import core.users.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder

@UseCase
class LoginUserUseCase(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userService: UserService = UserService()
) {
    fun execute(username: String, rawPassword: String): User {
        val user: User = userRepository.findByEmail(username)
            ?: throw UserNotFound(username)
        if (passwordEncoder.matches(rawPassword, user.password)) {
            return user
        }
        throw PasswordError()
    }
}