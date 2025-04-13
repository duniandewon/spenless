package com.ndewon.spendless.presentation.screens.repeatpin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ndewon.spendless.presentation.components.AppHeader
import com.ndewon.spendless.presentation.components.Numpad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatPinScreen(
    createdPin: String,
    onPinComplete: (String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: RepeatPinViewModel = viewModel()
) {
    val pin = viewModel.pin.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value

    LaunchedEffect(pin.length) {
        if (pin.length == 5) {
            if (pin != createdPin) {
                viewModel.setErrorMessage("PINs don’t match. Try again")
                viewModel.resetPin()
            } else {
                onPinComplete(pin)
            }
        }
    }

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
        },
    ) {
        Surface(modifier = Modifier.padding(it)) {
            RepeatPin(
                onClick = viewModel::onClickNum,
                pinLength = pin.length
            )
        }
    }
}

@Composable
fun RepeatPin(onClick: (String) -> Unit, pinLength: Int) {
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

        Numpad(onClick = onClick, pinLength = pinLength)
    }
}

@Preview(showBackground = true)
@Composable
fun RepeatPinPreview() {
    RepeatPin(
        onClick = {},
        pinLength = 0
    )
}