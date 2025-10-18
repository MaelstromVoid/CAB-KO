package cabko.technical

import core.users.entity.UserRole
import generated.enums.UserRoleEnum

fun UserRoleEnum.toDomainEnum(): UserRole =
    UserRole.entries.find { it.name == this.name }
        ?: throw IllegalArgumentException("No matching domain UserRole for $this")

fun UserRole.toJooqEnum(): UserRoleEnum =
    UserRoleEnum.entries.find { it.name == this.name }
        ?: throw IllegalArgumentException("No matching jOOQ UserRoleEnum for $this")
