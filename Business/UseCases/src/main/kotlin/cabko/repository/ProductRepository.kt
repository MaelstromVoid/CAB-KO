package cabko.repository

import common.identity.ProductID
import core.products.entity.Product

interface ProductRepository {
    fun findById(productID: ProductID,): Product?
    fun findAll(page: Int, size: Int, nameFilter: String?): List<Product>
    fun save(product: Product): Product
    fun deleteById(productID: ProductID,): Boolean
    fun decrementStock(productID: ProductID, amount: Int): Product?
}