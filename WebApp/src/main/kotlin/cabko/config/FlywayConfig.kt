package cabko.config

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.flyway.autoconfigure.FlywayMigrationStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(
    name = ["spring.flyway.clean-on-migration-validation-error"],
    havingValue = "true",
    matchIfMissing = false,
)
class FlywayConfig(
    private val log: KLogger = KotlinLogging.logger {},
) {

    @Bean
    fun cleanOnValidationErrorStrategy(): FlywayMigrationStrategy {
        return FlywayMigrationStrategy { flyway: Flyway ->
            try {
                flyway.validate()
            } catch (e: Exception) {
                log.info { "Flyway validation failed, cleaning DB. Error: ${e.message}" }
                flyway.clean()
            }
            flyway.migrate()
        }
    }
}
