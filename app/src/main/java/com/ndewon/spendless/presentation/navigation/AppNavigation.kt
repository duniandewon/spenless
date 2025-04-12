package com.ndewon.spendless.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ndewon.spendless.presentation.screens.createusername.CreateUserNameScreen
import com.ndewon.spendless.presentation.screens.createpin.CreatePinScreen
import kotlinx.serialization.Serializable

@Serializable
object CreateUsernameScreen

@Serializable
data class CreatePinScreen(val username: String)

@Serializable
data class RepeatPinScreen(val username: String, val pin: String)

@Serializable
data class SetPreferencesScreen(val username: String, val pin: String)


@Composable
fun AppNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CreateUsernameScreen
    ) {
        composable<CreateUsernameScreen> {
            CreateUserNameScreen(
                onCreateUsername = { username ->
                    navController.navigate(
                        CreatePinScreen(username = username)
                    )
                },
                onNavigateToLogin = {}
            )
        }

        composable<CreatePinScreen> {
            val pinScreen = it.toRoute<CreatePinScreen>()
            CreatePinScreen(
                onBackClick = { navController.popBackStack() },
                onPinComplete = { pin ->
                    navController.navigate(
                        RepeatPinScreen(
                            username = pinScreen.username,
                            pin = pin
                        )
                    )
                }
            )
        }

        composable<RepeatPinScreen> {
            val repeatPinScreen = it.toRoute<RepeatPinScreen>()

            Column {
                Text(text = "username: ${repeatPinScreen.username}")
                Text(text = "pin: ${repeatPinScreen.pin}")
            }
        }
    }

}