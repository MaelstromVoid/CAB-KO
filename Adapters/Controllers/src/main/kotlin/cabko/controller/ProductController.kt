package cabko.controller

import cabko.products.ProductRequestDTO
import cabko.products.ProductResponseDTO
import cabko.products.toResponseDTO
import cabko.security.JwtUserAuthentication
import cabko.usecase.products.AddProductUseCase
import cabko.usecase.products.DecrementProductStockUseCase
import cabko.usecase.products.DeleteProductUseCase
import cabko.usecase.products.ListProductsUseCase
import common.identity.ProductID
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val decrementProductStockUseCase: DecrementProductStockUseCase,
    private val listProductsUseCase: ListProductsUseCase
) {

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun add(
        authentication: Authentication,
        @RequestBody request: ProductRequestDTO
    ): ProductResponseDTO {
        val product = addProductUseCase.execute(
            authentication as JwtUserAuthentication, request.name, request.description, request.price, request.quantity
        )
        return product.toResponseDTO()
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun delete(
        authentication: Authentication,
        @PathVariable id: UUID
    ): ResponseEntity<Void> {
        deleteProductUseCase.execute(
            authentication as JwtUserAuthentication,
            ProductID(id),
        )
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}/decrement")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun decrement(
        authentication: Authentication,
        @PathVariable id: UUID,
        @RequestParam amount: Int
    ): ProductResponseDTO {
        val updatedProduct = decrementProductStockUseCase.execute(
            authentication as JwtUserAuthentication,
            ProductID(id),
            amount
        )
        return updatedProduct.toResponseDTO()
    }

    @GetMapping
    fun list(
        authentication: Authentication,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(required = false) filter: String?
    ): List<ProductResponseDTO> {
        return listProductsUseCase.execute(page, size, filter).map { product ->
            product.toResponseDTO()
        }
    }
}
