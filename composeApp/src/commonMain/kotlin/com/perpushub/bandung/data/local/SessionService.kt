package com.perpushub.bandung.data.local

import com.perpushub.bandung.data.model.Session
import com.perpushub.bandung.data.model.Token
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionService {
    private val settings = Settings()

    private val _session: MutableStateFlow<Session?> = MutableStateFlow(null)
    val session = _session.asStateFlow()

    init {
        val userId = settings.getInt("userId", 0)
        val accessToken = settings.getString("accessToken", "")
        val refreshToken = settings.getString("refreshToken", "")

        if (userId != 0 && accessToken.isNotBlank()) {
            _session.value = Session(userId, accessToken, refreshToken)
        }
    }

    fun save(session: Session) {
        settings.putInt("userId", session.userId)
        settings.putString("accessToken", session.accessToken)
        settings.putString("refreshToken", session.refreshToken)
        _session.value = session
    }

    fun updateToken(token: Token) {
        val session = _session.value
        if (session != null) {
            settings.putString("accessToken", token.accessToken)
            settings.putString("refreshToken", token.refreshToken)
            _session.value =
                session.copy(accessToken = token.accessToken, refreshToken = token.refreshToken)
        }
    }

    fun clear() {
        settings.remove("userId")
        settings.remove("accessToken")
        settings.remove("refreshToken")
        _session.value = null
    }
}