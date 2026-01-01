package com.perpushub.bandung.ui.main.delivery

sealed class DeliveryEvent {
    class OnErrorMessageClear() : DeliveryEvent()
}