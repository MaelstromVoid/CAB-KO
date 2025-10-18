package cabko.products

import cabko.common.RequestDTO
import java.math.BigDecimal

data class ProductRequestDTO(
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val quantity: Int
) : RequestDTO