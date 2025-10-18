package cabko.config

import org.jooq.SQLDialect
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

class JooqConfig(private val dataSource: DataSource) {

    @Bean
    fun jooq(): DefaultDSLContext {
        val configuration = DefaultConfiguration()
        configuration.set(dataSource)
        configuration.set(SQLDialect.POSTGRES)
        return DefaultDSLContext(configuration)
    }

}