package cabko.repository

import core.users.entity.User

interface UserRepository {
    fun findByEmail(email: String): User?
    fun save(user: User): User
}