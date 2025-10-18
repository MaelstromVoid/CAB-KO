package cabko.usecase.products

import cabko.repository.ProductRepository
import cabko.technical.TransactionalHandler
import cabko.technical.UseCase
import common.error.ProductNotFound
import common.identity.ProductID
import core.users.model.AuthenticatedUser
import core.users.service.ActionAuthorizationService
import core.users.service.data.RequestType

@UseCase
class DeleteProductUseCase(
    private val actionAuthorizationService: ActionAuthorizationService = ActionAuthorizationService(),
    private val transactionalHandler: TransactionalHandler,
    private val productRepository: ProductRepository,
) {
    fun execute(
        user: AuthenticatedUser,
        productID: ProductID,
    ) =
        transactionalHandler.runTransaction {
            actionAuthorizationService.requestValidator(RequestType.DELETE_PRODUCT, user)
            val deleted = productRepository.deleteById(productID)
            if (!deleted) {
                throw ProductNotFound(productID)
            }
        }
}