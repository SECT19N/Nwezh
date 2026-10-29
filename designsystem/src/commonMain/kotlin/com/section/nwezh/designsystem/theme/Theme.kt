package com.section.nwezh.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

enum class ThemeMode { System, Light, Dark }

/** Whether this mode renders dark, given the system setting. */
fun ThemeMode.isDark(systemDark: Boolean): Boolean = when (this) {
    ThemeMode.System -> systemDark
    ThemeMode.Light -> false
    ThemeMode.Dark -> true
}

/**
 * App theme: fixed plum palette (no dynamic color), Material 3 Expressive motion and shapes.
 *
 * The UI font follows the layout direction: RTL languages are Arabic script and use Noto Sans Arabic,
 * the others use Roboto Flex. The caller decides the direction from the chosen language.
 */
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NwezhTheme(
    mode: ThemeMode = ThemeMode.System,
    content: @Composable () -> Unit,
) {
    val dark = mode.isDark(isSystemInDarkTheme())
    val family = if (LocalLayoutDirection.current == LayoutDirection.Rtl) {
        notoSansArabicFamily()
    } else {
        robotoFlexFamily()
    }
    MaterialExpressiveTheme(
        colorScheme = if (dark) NwezhDarkColors else NwezhLightColors,
        motionScheme = MotionScheme.expressive(),
        shapes = NwezhMaterialShapes,
        typography = nwezhTypography(family),
        content = content,
    )
}
