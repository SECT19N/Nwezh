package com.section.nwezh.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Tokens from the v3 prototype. Roles marked "derived" are not in the spec and were picked to sit
// with the plum primary.

internal val NwezhLightColors: ColorScheme = lightColorScheme(
    primary = Color(0xFF7B4F72),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFD7F3),
    onPrimaryContainer = Color(0xFF301029),
    inversePrimary = Color(0xFFECB4E2), // derived
    secondary = Color(0xFF6E5868), // derived
    onSecondary = Color(0xFFFFFFFF), // derived
    secondaryContainer = Color(0xFFF4DBEB),
    onSecondaryContainer = Color(0xFF261622),
    tertiary = Color(0xFF805644), // derived
    onTertiary = Color(0xFFFFFFFF), // derived
    tertiaryContainer = Color(0xFFFFDAD2),
    onTertiaryContainer = Color(0xFF2C150F),
    background = Color(0xFFFFF7FA),
    onBackground = Color(0xFF201A1E),
    surface = Color(0xFFFFF7FA),
    onSurface = Color(0xFF201A1E),
    surfaceVariant = Color(0xFFEDDFE7), // derived
    onSurfaceVariant = Color(0xFF4F444B),
    surfaceTint = Color(0xFF7B4F72),
    inverseSurface = Color(0xFF362E33),
    inverseOnSurface = Color(0xFFFBEEF4), // derived
    error = Color(0xFFBA1A1A), // derived
    onError = Color(0xFFFFFFFF), // derived
    errorContainer = Color(0xFFFFDAD6), // derived
    onErrorContainer = Color(0xFF410002), // derived
    outline = Color(0xFF81737B),
    outlineVariant = Color(0xFFD3C2CB),
    scrim = Color(0xFF140A10),
    surfaceBright = Color(0xFFFFF7FA), // derived
    surfaceDim = Color(0xFFE9DFE4), // the prototype's "page" colour
    surfaceContainerLowest = Color(0xFFFFFFFF), // derived
    surfaceContainerLow = Color(0xFFFBF0F5),
    surfaceContainer = Color(0xFFF5EAF0),
    surfaceContainerHigh = Color(0xFFEFE4EA),
    surfaceContainerHighest = Color(0xFFE9DEE4), // derived
)

internal val NwezhDarkColors: ColorScheme = darkColorScheme(
    primary = Color(0xFFECB4E2),
    onPrimary = Color(0xFF47203F),
    primaryContainer = Color(0xFF603857),
    onPrimaryContainer = Color(0xFFFFD7F3),
    inversePrimary = Color(0xFF7B4F72), // derived
    secondary = Color(0xFFD7BFD0), // derived
    onSecondary = Color(0xFF3B2A37), // derived
    secondaryContainer = Color(0xFF52404D),
    onSecondaryContainer = Color(0xFFF4DBEB),
    tertiary = Color(0xFFF4B8A8), // derived
    onTertiary = Color(0xFF442A22), // derived
    tertiaryContainer = Color(0xFF5E3A32),
    onTertiaryContainer = Color(0xFFFFDAD2),
    background = Color(0xFF181216),
    onBackground = Color(0xFFECDFE6),
    surface = Color(0xFF181216),
    onSurface = Color(0xFFECDFE6),
    surfaceVariant = Color(0xFF4F444B), // derived
    onSurfaceVariant = Color(0xFFD2C2CB),
    surfaceTint = Color(0xFFECB4E2),
    inverseSurface = Color(0xFFECDFE6),
    inverseOnSurface = Color(0xFF362E33), // derived
    error = Color(0xFFFFB4AB), // derived
    onError = Color(0xFF690005), // derived
    errorContainer = Color(0xFF93000A), // derived
    onErrorContainer = Color(0xFFFFDAD6), // derived
    outline = Color(0xFF9B8D95),
    outlineVariant = Color(0xFF4F444B),
    scrim = Color(0xFF000000),
    surfaceBright = Color(0xFF3A3237), // derived
    surfaceDim = Color(0xFF0F0B0E), // the prototype's "page" colour
    surfaceContainerLowest = Color(0xFF130D11), // derived
    surfaceContainerLow = Color(0xFF201A1E),
    surfaceContainer = Color(0xFF251E23),
    surfaceContainerHigh = Color(0xFF30282D),
    surfaceContainerHighest = Color(0xFF3B3338), // derived
)
