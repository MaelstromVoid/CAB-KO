package cabko.users

import cabko.common.ResponseDTO

data class AuthResponseDTO(
    val token: String
) : ResponseDTO