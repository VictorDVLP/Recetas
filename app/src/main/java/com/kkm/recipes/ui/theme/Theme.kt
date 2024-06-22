package com.kkm.recipes.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PastelPurple,
    onPrimary = Color.White,
    primaryContainer = PastelPink,
    onPrimaryContainer = PastelBlue,
    secondary = MutedSapphire,
    onSecondary = Color.White,
    secondaryContainer = EarthyTaupe,
    onSecondaryContainer = CalmingAqua,
    tertiary = CalmingNavy,
    onTertiary = Color.White,
    tertiaryContainer = CalmingLavender,
    onTertiaryContainer = CalmingGray,
    error = MutedGarnet,
    errorContainer = PastelYellow,
    onError = Color.White,
    onErrorContainer = SoftBrown,
    background = EarthyBeige,
    onBackground = EarthyBrown,
    surface = EarthyBeige,
    onSurface = EarthyBrown,
    surfaceVariant = EarthyTaupe,
    onSurfaceVariant = EarthyOlive,
    outline = SoftOlive,
    inverseOnSurface = EarthyBrown,
    inverseSurface = EarthyBeige,
    inversePrimary = PastelBlue
)

private val LightColorScheme = lightColorScheme(
    primary = EarthyOlive,
    onPrimary = Color.White,
    primaryContainer = EarthyOlive,
    onPrimaryContainer = Color.White,
    secondary = MutedEmerald,
    onSecondary = Color.White,
    secondaryContainer = CalmingTeal,
    onSecondaryContainer = Color.White,
    tertiary = EarthyBrown,
    onTertiary = Color.White,
    tertiaryContainer = CalmingLavender,
    onTertiaryContainer = CalmingGray,
    error = MutedGarnet,
    errorContainer = PastelYellow,
    onError = Color.White,
    onErrorContainer = SoftBrown,
    background = Color.White,
    onBackground = EarthyBrown,
    surface = Color.White,
    onSurface = Color.White,
    surfaceVariant = EarthyTaupe,
    onSurfaceVariant = Color.White,
    outline = SoftOlive,
    inverseOnSurface = SoftTaupe,
    inverseSurface = Color.White,
    inversePrimary = PastelGreen
)

@Composable
fun RecipesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}