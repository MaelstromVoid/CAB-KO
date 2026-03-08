package cabko.repository.users

import cabko.repository.UserRepository
import cabko.technical.toDomainEnum
import cabko.technical.toJooqEnum
import common.identity.UserID
import core.users.entity.User
import generated.tables.Users.USERS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class JooqUserRepository(private val jooq: DSLContext) : UserRepository {

    override fun findByEmail(email: String): User? {
        val record = jooq.selectFrom(USERS)
            .where(USERS.EMAIL.eq(email))
            .fetchOne() ?: return null

        return User(
            userID = UserID(
                record[USERS.ID]
            ),
            email = record[USERS.EMAIL],
            password = record[USERS.PASSWORD],
            userRoles = record[USERS.ROLES].map { it.toDomainEnum() }.toSet(),
        )
    }

    override fun save(user: User): User {
        jooq.insertInto(USERS)
            .columns(
                USERS.ID,
                USERS.EMAIL,
                USERS.PASSWORD,
                USERS.ROLES
            )
            .values(
                user.userID.id,
                user.email,
                user.password,
                user.userRoles.map { it.toJooqEnum() }.toTypedArray()
            )
            .execute()
        return user
    }
}