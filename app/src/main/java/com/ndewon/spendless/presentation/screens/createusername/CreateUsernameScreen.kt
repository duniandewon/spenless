package com.ndewon.spendless.presentation.screens.createusername

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndewon.spendless.presentation.components.AppHeader
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateUserNameScreen(
    modifier: Modifier = Modifier,
    onCreateUsername: (String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val viewModel: CreateUserNameViewModel = koinViewModel()

    val username = viewModel.username.collectAsState().value
    val userCreated = viewModel.userCreated.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(userCreated) {
        if (userCreated) {
            onCreateUsername(username)
        }
    }

    Box(modifier = Modifier.imePadding()) {
        Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
            Surface(
                modifier = Modifier.padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier.padding(start = 26.dp, end = 26.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppHeader(
                        title = "Welcome to SpendLess! How can we address you?",
                        subtitle = "Create a unique username"
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    TextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        value = username,
                        placeholder = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "username",
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
                                    fontWeight = MaterialTheme.typography.displayMedium.fontWeight,
                                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                                )
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            if (username.isNotEmpty()) viewModel.createUser()
                        }),
                        onValueChange = { viewModel.setUsername(it) },
                        shape = RoundedCornerShape(16.dp),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
                            fontWeight = MaterialTheme.typography.displayMedium.fontWeight,
                            fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                        ),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        onClick = { viewModel.createUser() },
                        enabled = username.isNotEmpty(),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    lineHeight = MaterialTheme.typography.titleMedium.lineHeight,
                                ),
                                text = "Next"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Next",
                                tint = if (username.isNotEmpty()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.12f
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    TextButton(onClick = { onNavigateToLogin() }) {
                        Text(
                            text = "Already have an account?",
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                lineHeight = MaterialTheme.typography.titleMedium.lineHeight,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            visible = errorMessage.isNotEmpty()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.error)
            ) {
                Text(
                    text = errorMessage,
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

@Preview(showBackground = true)
@Composable
fun CreateUserNameScreenPreview() {
    CreateUserNameScreen(
        onCreateUsername = {},
        onNavigateToLogin = {}
    )
}