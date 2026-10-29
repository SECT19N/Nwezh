package com.section.nwezh.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.section.nwezh.designsystem.resources.Res
import com.section.nwezh.designsystem.resources.amiri_quran
import com.section.nwezh.designsystem.resources.noto_naskh_arabic_bold
import com.section.nwezh.designsystem.resources.noto_naskh_arabic_medium
import com.section.nwezh.designsystem.resources.noto_naskh_arabic_regular
import com.section.nwezh.designsystem.resources.noto_naskh_arabic_semibold
import com.section.nwezh.designsystem.resources.noto_sans_arabic_bold
import com.section.nwezh.designsystem.resources.noto_sans_arabic_medium
import com.section.nwezh.designsystem.resources.noto_sans_arabic_regular
import com.section.nwezh.designsystem.resources.noto_sans_arabic_semibold
import com.section.nwezh.designsystem.resources.roboto_flex
import org.jetbrains.compose.resources.Font

private val Weights =
    listOf(FontWeight.Normal, FontWeight.Medium, FontWeight.SemiBold, FontWeight.Bold)

/** Roboto Flex, a variable font: one file, a weight axis per [FontWeight]. */
@Composable
fun robotoFlexFamily(): FontFamily = FontFamily(
    Weights.map { weight ->
        Font(
            Res.font.roboto_flex,
            weight = weight,
            variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)),
        )
    },
)

/** Default UI font for Arabic-script languages (Arabic, Sorani, Badini). */
@Composable
fun notoSansArabicFamily(): FontFamily = FontFamily(
    Font(Res.font.noto_sans_arabic_regular, FontWeight.Normal),
    Font(Res.font.noto_sans_arabic_medium, FontWeight.Medium),
    Font(Res.font.noto_sans_arabic_semibold, FontWeight.SemiBold),
    Font(Res.font.noto_sans_arabic_bold, FontWeight.Bold),
)

/** Naskh alternative for running Arabic-script text. */
@Composable
fun notoNaskhArabicFamily(): FontFamily = FontFamily(
    Font(Res.font.noto_naskh_arabic_regular, FontWeight.Normal),
    Font(Res.font.noto_naskh_arabic_medium, FontWeight.Medium),
    Font(Res.font.noto_naskh_arabic_semibold, FontWeight.SemiBold),
    Font(Res.font.noto_naskh_arabic_bold, FontWeight.Bold),
)

@Composable
fun amiriQuranFamily(): FontFamily = FontFamily(Font(Res.font.amiri_quran, FontWeight.Normal))

/** Material type scale with [family] for every style. */
internal fun nwezhTypography(family: FontFamily): Typography {
    val base = Typography()
    fun TextStyle.withFamily() = copy(fontFamily = family)
    return base.copy(
        displayLarge = base.displayLarge.withFamily(),
        displayMedium = base.displayMedium.withFamily(),
        displaySmall = base.displaySmall.withFamily(),
        headlineLarge = base.headlineLarge.withFamily(),
        headlineMedium = base.headlineMedium.withFamily(),
        headlineSmall = base.headlineSmall.withFamily(),
        titleLarge = base.titleLarge.withFamily(),
        titleMedium = base.titleMedium.withFamily(),
        titleSmall = base.titleSmall.withFamily(),
        bodyLarge = base.bodyLarge.withFamily(),
        bodyMedium = base.bodyMedium.withFamily(),
        bodySmall = base.bodySmall.withFamily(),
        labelLarge = base.labelLarge.withFamily(),
        labelMedium = base.labelMedium.withFamily(),
        labelSmall = base.labelSmall.withFamily(),
    )
}

const val DefaultQuranFontSizeSp = 30

/** Style for Quran verses. Arabic with diacritics needs generous line height. */
@Composable
fun quranTextStyle(fontSize: TextUnit = DefaultQuranFontSizeSp.sp): TextStyle {
    val family = amiriQuranFamily()
    return remember(family, fontSize) {
        TextStyle(
            fontFamily = family,
            fontSize = fontSize,
            lineHeight = fontSize * 1.9f,
        )
    }
}
