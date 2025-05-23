package com.ndewon.spendless.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val LightColorScheme = lightColorScheme(
    primary = Purple,
    onPrimary = White,
    primaryContainer = Violet,
    onPrimaryContainer = DeepPurple,
    inversePrimary = LightPurple,
    background = VeryLightPink,
    onBackground = DeepCharcoal,
    surface = OffWhite,
    surfaceContainer = MediumLightGray,
    inverseSurface = Charcoal,
    onSurface = AlmostBlack,
    inverseOnSurface = PaleWhite,
    error = DarkRed,
    onError = White,
    tertiaryContainer = SoftBlue,
    secondary = OliveGreen,
    onSecondary = DarkOliveGreen,
    secondaryContainer = LightYellowGreen,
    onSecondaryContainer = DarkOliveGreen,
    onSurfaceVariant = DarkGray,
)

val DarkColorScheme = darkColorScheme(
    primary = LightPurple,
    onPrimary = White,
    primaryContainer = DarkViolet,
    onPrimaryContainer = Lavender,
    inversePrimary = Purple,
    background = AlmostBlack,
    onBackground = PaleWhite,
    surface = Charcoal,
    onSurface = LightGray,
    inverseSurface = MediumGray,
    onSurfaceVariant = DarkGray,
    inverseOnSurface = VeryLightPink,
    error = DarkRed,
    onError = White,
    tertiaryContainer = SoftBlue,
    secondary = MutedYellowGreen,
    onSecondary = PaleWhite,
    secondaryContainer = DarkOliveGreen,
    onSecondaryContainer = LightYellowGreen
)

@Composable
fun SpendLessTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}