package com.perpushub.bandung.service.session

import com.perpushub.bandung.common.model.Session
import com.perpushub.bandung.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionManager(
    private val authRepository: AuthRepository
) {
    private val _session: MutableStateFlow<Session?> = MutableStateFlow(null)
    val session = _session.asStateFlow()

    suspend fun login(email: String, password: String) {
        _session.value = authRepository.login(email, password)
    }

    fun logout() {
        _session.value = null
    }
}