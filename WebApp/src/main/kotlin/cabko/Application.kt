package cabko

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["cabko"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
