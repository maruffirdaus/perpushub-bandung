package com.perpushub.bandung.ui.auth.register

sealed class RegisterEvent {
    class OnUsernameChange(val username: String) : RegisterEvent()
    class OnFullNameChange(val fullName: String) : RegisterEvent()
    class OnEmailChange(val email: String) : RegisterEvent()
    class OnPasswordChange(val password: String) : RegisterEvent()
    object OnPasswordVisibilityToggle : RegisterEvent()
    class OnConfirmPasswordChange(val password: String) : RegisterEvent()
    object OnConfirmPasswordVisibilityToggle : RegisterEvent()
    class OnRegister(val onSuccess: () -> Unit) : RegisterEvent()
}