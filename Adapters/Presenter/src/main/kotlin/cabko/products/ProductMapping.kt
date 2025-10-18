package cabko.products

import core.products.entity.Product

fun Product.toResponseDTO(): ProductResponseDTO = ProductResponseDTO(
    id = productID.id,
    name = name,
    description = description,
    price = price,
    quantity = quantity
)