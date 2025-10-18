package cabko.technical

import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionTemplate

@Component
class TransactionalHandler(
    private val transactionTemplate: TransactionTemplate
) {
    fun <T> runTransaction(isolationLevel: IsolationLevel = IsolationLevel.ISOLATION_SERIALIZABLE, block: () -> T): T {
        transactionTemplate.isolationLevel = isolationLevel.isolation
        return transactionTemplate.execute {
            block()
        } ?: throw TransactionException()
    }
}