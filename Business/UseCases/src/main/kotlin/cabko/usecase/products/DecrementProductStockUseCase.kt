package cabko.usecase.products

import cabko.repository.ProductRepository
import cabko.technical.TransactionalHandler
import cabko.technical.UseCase
import common.error.ProductNotFound
import common.identity.ProductID
import core.products.entity.Product
import core.users.model.AuthenticatedUser
import core.users.service.ActionAuthorizationService
import core.users.service.data.RequestType

@UseCase
class DecrementProductStockUseCase(
    private val actionAuthorizationService: ActionAuthorizationService = ActionAuthorizationService(),
    private val transactionalHandler: TransactionalHandler,
    private val productRepository: ProductRepository,
) {

    fun execute(
        user: AuthenticatedUser,
        productID: ProductID,
        amount: Int
    ): Product =
        transactionalHandler.runTransaction {
            actionAuthorizationService.requestValidator(RequestType.UPDATE_PRODUCT, user)
            productRepository.decrementStock(productID, amount) ?: throw ProductNotFound(productID)
        }
}