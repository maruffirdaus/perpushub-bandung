package com.perpushub.bandung.data.local

import com.perpushub.bandung.data.model.Session
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

        if (userId != 0 && accessToken.isNotBlank()) {
            _session.value = Session(userId, accessToken)
        }
    }

    fun save(session: Session) {
        settings.putInt("userId", session.userId)
        settings.putString("accessToken", session.accessToken)
        _session.value = session
    }

    fun clear() {
        settings.remove("userId")
        settings.remove("accessToken")
        _session.value = null
    }
}