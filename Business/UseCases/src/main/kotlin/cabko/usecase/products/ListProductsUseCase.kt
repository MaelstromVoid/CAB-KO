package cabko.usecase.products

import cabko.repository.ProductRepository
import cabko.technical.UseCase
import core.products.entity.Product

@UseCase
class ListProductsUseCase(private val productRepository: ProductRepository) {
    fun execute(page: Int, size: Int, nameFilter: String?): List<Product> {
        return productRepository.findAll(page, size, nameFilter)
    }
}