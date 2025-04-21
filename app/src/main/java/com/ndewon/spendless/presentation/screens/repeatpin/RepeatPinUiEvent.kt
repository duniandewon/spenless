package com.ndewon.spendless.presentation.screens.repeatpin

sealed class RepeatPinUiEvent {
    data object CreateUser : RepeatPinUiEvent()
    data class OnClickNum(val num: String) : RepeatPinUiEvent()
    data class CheckPinMisMatch(val prevPin: String) : RepeatPinUiEvent()
    data object ClearErrorMessage : RepeatPinUiEvent()
}