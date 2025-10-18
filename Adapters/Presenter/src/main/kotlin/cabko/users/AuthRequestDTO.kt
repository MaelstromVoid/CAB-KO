package cabko.users

import cabko.common.RequestDTO

data class AuthRequestDTO(
    val email: String,
    val password: String
) : RequestDTO