package cabko.usecase.products

import cabko.repository.ProductRepository
import cabko.technical.TransactionalHandler
import cabko.technical.UseCase
import common.identity.ProductID
import core.products.entity.Product
import core.users.model.AuthenticatedUser
import core.users.service.ActionAuthorizationService
import core.users.service.data.RequestType
import java.math.BigDecimal
import java.util.UUID

@UseCase
class AddProductUseCase(
    private val actionAuthorizationService: ActionAuthorizationService = ActionAuthorizationService(),
    private val transactionalHandler: TransactionalHandler,
    private val productRepository: ProductRepository,
) {
    fun execute(
        user: AuthenticatedUser,
        name: String,
        description: String?,
        price: BigDecimal,
        quantity: Int
    ): Product =
        transactionalHandler.runTransaction {
            actionAuthorizationService.requestValidator(RequestType.ADD_PRODUCT, user)
            val product = Product(ProductID(UUID.randomUUID()), name, description, price, quantity)
            productRepository.save(product)
        }

}