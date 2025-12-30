package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.User
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class UserRepository {
    suspend fun getUser(id: Int): User? {
        delay(0.25.seconds)
        return User.dummies[id]
    }
}