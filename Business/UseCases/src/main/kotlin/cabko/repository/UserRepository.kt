package cabko.repository

import core.users.entity.User

interface UserRepository {
    fun findByUsername(username: String): User?
    fun save(user: User): User
}