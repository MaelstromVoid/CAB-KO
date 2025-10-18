package cabko.technical

import org.springframework.transaction.TransactionDefinition

/**
 * Represents the transaction isolation levels available in Spring's transaction management.
 *
 * <p>Each enum constant maps to a corresponding {@link org.springframework.transaction.TransactionDefinition}
 * isolation level. Transaction isolation defines the degree to which a transaction must be isolated
 * from data modifications made by other transactions, affecting phenomena such as dirty reads,
 * non-repeatable reads, and phantom reads.</p>
 *
 * <p>For detailed information on how isolation levels behave in your specific database, refer to
 * your database documentation. For example, PostgreSQL:
 * <a href="https://www.postgresql.org/docs/current/transaction-iso.html">PostgreSQL Transaction Isolation</a>.</p>
 *
 * @property isolation the integer value of the isolation level as defined by Spring's TransactionDefinition
 */
enum class IsolationLevel(val isolation: Int) {
    ISOLATION_DEFAULT(TransactionDefinition.ISOLATION_DEFAULT),
    ISOLATION_READ_UNCOMMITTED(TransactionDefinition.ISOLATION_READ_UNCOMMITTED),
    ISOLATION_READ_COMMITTED(TransactionDefinition.ISOLATION_READ_COMMITTED),
    ISOLATION_REPEATABLE_READ(TransactionDefinition.ISOLATION_REPEATABLE_READ),
    ISOLATION_SERIALIZABLE(TransactionDefinition.ISOLATION_SERIALIZABLE),
}