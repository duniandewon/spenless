package com.ndewon.spendless.presentation.screens.repeatpin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RepeatPinViewModel : ViewModel() {
    private val _pin = MutableStateFlow("")
    val pin: StateFlow<String> = _pin.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _pin.asStateFlow()

    fun onClickNum(pin: String) {
        if (_pin.value.length >= 5) return;

        if (pin == "X") {
            _pin.value = _pin.value.dropLast(1)
        } else {
            _pin.value += pin
        }
    }

    fun resetPin() {
        _pin.value = ""
    }

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }
}