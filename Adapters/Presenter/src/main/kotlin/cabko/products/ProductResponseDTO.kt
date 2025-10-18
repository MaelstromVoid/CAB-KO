package cabko.products

import cabko.common.ResponseDTO
import java.math.BigDecimal
import java.util.UUID

data class ProductResponseDTO(
    val id: UUID,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val quantity: Int
) : ResponseDTO