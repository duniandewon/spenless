package com.ndewon.spendless.presentation.screens.repeatpin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndewon.spendless.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepeatPinViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<RepeatPinUiState>(RepeatPinUiState.RepeatPin("", ""))
    val uiState: StateFlow<RepeatPinUiState> = _uiState.asStateFlow()

    fun onEvent(event: RepeatPinUiEvent) {
        val curState = _uiState.value as? RepeatPinUiState.RepeatPin ?: return

        when (event) {
            RepeatPinUiEvent.CreateUser -> {
                _uiState.value = RepeatPinUiState.Success
            }

            is RepeatPinUiEvent.CheckPinMisMatch -> {
                if (checkPinMisMatch(event.prevPin)) {
                    updateState { it.copy(errorMessage = "PINs don’t match. Try again", pin = "") }
                } else {
                    createPin(curState.userName, curState.pin)
                }
            }

            is RepeatPinUiEvent.OnClickNum -> {
                updateState { state ->
                    if (event.num == "X") state.copy(pin = state.pin.dropLast(1))
                    else state.copy(pin = state.pin + event.num)
                }
            }

            RepeatPinUiEvent.ClearErrorMessage -> {
                updateState { it.copy(errorMessage = "") }
            }
        }
    }

    fun checkPinMisMatch(createdPin: String): Boolean {
        val currentState = _uiState.value as? RepeatPinUiState.RepeatPin ?: return false
        return currentState.pin != createdPin
    }

    fun updateState(update: (RepeatPinUiState.RepeatPin) -> RepeatPinUiState.RepeatPin) {
        _uiState.value =
            (_uiState.value as? RepeatPinUiState.RepeatPin)?.let(update) ?: _uiState.value
    }

    fun createPin(username: String, pin: String) = viewModelScope.launch {
        try {
            userRepository.createPin(username, pin)
            onEvent(RepeatPinUiEvent.CreateUser)
        } catch (e: Exception) {
            updateState {
                it.copy(errorMessage = e.message ?: "Unknown error")
            }
        }
    }
}