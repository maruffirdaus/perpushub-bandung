package com.perpushub.bandung.ui.auth.login

sealed class LoginEvent {
    class OnEmailChange(val email: String) : LoginEvent()
    class OnPasswordChange(val password: String) : LoginEvent()
    object OnPasswordVisibilityToggle : LoginEvent()
    object OnLogin : LoginEvent()
}