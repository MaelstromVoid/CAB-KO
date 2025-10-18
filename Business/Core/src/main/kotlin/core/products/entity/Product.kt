package core.products.entity

import common.Entity
import common.identity.ProductID
import java.math.BigDecimal

data class Product(
    val productID: ProductID,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val quantity: Int
) : Entity