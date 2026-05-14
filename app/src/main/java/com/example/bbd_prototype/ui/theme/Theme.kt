package com.example.bbd_prototype.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BurntOrange,
    secondary = SoftBrown,
    tertiary = DoughTan
)

private val LightColorScheme = lightColorScheme(
    primary = BurntOrange,
    secondary = SoftBrown,
    tertiary = DoughTan,

    background = BakeryCream,
    surface = BakerySurface,
    surfaceVariant = BakeryCard,

    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = TextDarkBrown,
    onBackground = TextDarkBrown,
    onSurface = TextDarkBrown,
    onSurfaceVariant = TextMutedBrown
)

@Composable
fun BBD_PrototypeTheme(
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