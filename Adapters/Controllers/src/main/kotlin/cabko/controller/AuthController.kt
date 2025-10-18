package cabko.controller

import cabko.security.JwtTokenProvider
import cabko.usecase.users.LoginUserUseCase
import cabko.usecase.users.RegisterUserUseCase
import cabko.users.AuthRequestDTO
import cabko.users.AuthResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val jwtTokenProvider: JwtTokenProvider
) {


    @PostMapping("/register")
    fun register(@RequestBody request: AuthRequestDTO): ResponseEntity<String> {
        registerUserUseCase.execute(request.email, request.password)
        return ResponseEntity.ok("User registered successfully")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequestDTO): AuthResponseDTO {
        val user = loginUserUseCase.execute(request.email, request.password)
        val token = jwtTokenProvider.createToken(user.userID,user.email, user.userRoles)
        return AuthResponseDTO(token)
    }
}
