package com.perpushub.bandung.ui.common.messaging

data class UiError(
    override val message: String,
    override val title: String = "Terjadi kesalahan"
) : UiMessage
