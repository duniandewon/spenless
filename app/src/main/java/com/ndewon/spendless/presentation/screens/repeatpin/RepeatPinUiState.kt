package com.ndewon.spendless.presentation.screens.repeatpin

sealed class RepeatPinUiState {
    object Success : RepeatPinUiState()
    data class RepeatPin(
        val userName: String = "",
        val pin: String = "",
        val errorMessage: String = ""
    ) :
        RepeatPinUiState()
}