package com.ndewon.spendless.presentation.screens.createusername

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateUserNameViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _userCreated = MutableStateFlow(false)
    val userCreated: StateFlow<Boolean> = _userCreated

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun setUsername(username: String) {
        _username.value = username
        _errorMessage.value = ""
    }

    fun createUser() {
        viewModelScope.launch {
            val res = userRepository.getUserByUsername(_username.value)
            if (res is Result.Success) {
                _errorMessage.value = "This username has already been taken!"
                return@launch;
            }

            _userCreated.value = true
            _errorMessage.value = ""
        }
    }
}
