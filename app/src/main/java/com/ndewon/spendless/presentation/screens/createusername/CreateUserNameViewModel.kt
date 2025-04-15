package com.ndewon.spendless.presentation.screens.createusername

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndewon.spendless.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateUserNameViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _userCreated = MutableStateFlow(false)
    val userCreated: StateFlow<Boolean> = _userCreated

    fun setUsername(username: String) {
        _username.value = username
    }

    fun createUser() {
        viewModelScope.launch {
            try {
                userRepository.createUser(_username.value)
                _userCreated.value = true
            } catch (e: Exception) {
                Log.d("CreateUserNameViewModel", "createUser: ${e.message ?: "Unknown error"}")
            }
        }
    }
}