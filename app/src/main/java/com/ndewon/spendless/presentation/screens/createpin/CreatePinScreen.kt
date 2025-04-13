package com.ndewon.spendless.presentation.screens.createpin

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ndewon.spendless.presentation.components.AppHeader
import com.ndewon.spendless.presentation.components.Numpad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePinScreen(
    viewModel: CreatePinViewModel = viewModel(),
    onPinComplete: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val pin = viewModel.pin.collectAsState().value

    LaunchedEffect(pin.length) {
        if (pin.length == 5) {
            viewModel.resetPin()
            onPinComplete(pin)
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
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            CreatePin(
                onClick = viewModel::onClickNum,
                pinLength = pin.length
            )
        }
    }
}

@Composable
fun CreatePin(onClick: (String) -> Unit, pinLength: Int) {
    Column(
        modifier = Modifier
            .padding(start = 26.dp, end = 26.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader(
            title = "Create PIN",
            subtitle = "Use PIN to login to your account"
        )

        Spacer(modifier = Modifier.height(32.dp))

        Numpad(onClick = onClick, pinLength = pinLength)
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePinScreenPreview() {
    CreatePin(
        onClick = {},
        pinLength = 0
    )
}