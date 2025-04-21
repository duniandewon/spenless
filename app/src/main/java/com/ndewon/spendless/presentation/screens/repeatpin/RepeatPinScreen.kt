package com.ndewon.spendless.presentation.screens.repeatpin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ndewon.spendless.presentation.components.AppHeader
import com.ndewon.spendless.presentation.components.Numpad
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RepeatPinScreen(
    username: String,
    createdPin: String,
    onPinComplete: () -> Unit,
    onBackClick: () -> Unit,
) {
    val viewModel: RepeatPinViewModel = koinViewModel()

    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.updateState { it.copy(userName = username) }
    }

    when (val state = uiState) {
        is RepeatPinUiState.RepeatPin -> {
            RepeatPin(
                createdPin = createdPin,
                onBackClick = onBackClick,
                onEvent = viewModel::onEvent,
                uiState = state
            )
        }

        RepeatPinUiState.Success -> {
            onPinComplete()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatPin(
    createdPin: String,
    onBackClick: () -> Unit,
    onEvent: (RepeatPinUiEvent) -> Unit,
    uiState: RepeatPinUiState.RepeatPin
) {
    LaunchedEffect(uiState.pin) {
        if (uiState.pin.length == 5) {
            onEvent(RepeatPinUiEvent.CheckPinMisMatch(createdPin))
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNotEmpty()) {
            delay(5000)
            onEvent(RepeatPinUiEvent.ClearErrorMessage)
        }
    }
    Box {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) {
            Surface(modifier = Modifier.padding(it)) {
                Column(
                    modifier = Modifier
                        .padding(start = 26.dp, end = 26.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppHeader(
                        title = "Repeat Your PIN",
                        subtitle = "Enter your PIN again"
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Numpad(
                        onClick = { onEvent(RepeatPinUiEvent.OnClickNum(it)) },
                        pinLength = uiState.pin.length
                    )
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            visible = uiState.errorMessage.isNotEmpty()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(MaterialTheme.colorScheme.error)
            ) {
                Text(
                    text = uiState.errorMessage,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )
            }
        }

    }

}