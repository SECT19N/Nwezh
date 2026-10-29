package com.section.nwezh.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.section.nwezh.designsystem.components.NwezhBottomSheet
import com.section.nwezh.designsystem.components.NwezhConnectedColumn
import com.section.nwezh.designsystem.components.NwezhNavRow
import com.section.nwezh.designsystem.components.NwezhStepperRow
import com.section.nwezh.designsystem.components.NwezhSwitchRow
import com.section.nwezh.designsystem.components.NwezhTab
import com.section.nwezh.designsystem.components.NwezhTabBar
import com.section.nwezh.designsystem.icons.NwezhIcon
import com.section.nwezh.designsystem.icons.NwezhIcons
import com.section.nwezh.designsystem.theme.NwezhShapes
import com.section.nwezh.designsystem.theme.NwezhTheme
import com.section.nwezh.designsystem.theme.ThemeMode
import com.section.nwezh.designsystem.theme.quranTextStyle

@Composable
fun CatalogApp() {
    var mode by rememberSaveable { mutableStateOf(ThemeMode.System) }
    var rtl by rememberSaveable { mutableStateOf(false) }
    CompositionLocalProvider(
        LocalLayoutDirection provides if (rtl) LayoutDirection.Rtl else LayoutDirection.Ltr,
    ) {
        NwezhTheme(mode = mode) {
            CatalogScreen(mode, { mode = it }, rtl, { rtl = it })
        }
    }
}

@Composable
private fun CatalogScreen(
    mode: ThemeMode,
    onMode: (ThemeMode) -> Unit,
    rtl: Boolean,
    onRtl: (Boolean) -> Unit,
) {
    var tab by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(
        NwezhTab(NwezhIcons.Home, "Today"),
        NwezhTab(NwezhIcons.Quran, "Quran"),
        NwezhTab(NwezhIcons.Qibla, "Qibla"),
        NwezhTab(NwezhIcons.Calendar, "Calendar"),
        NwezhTab(NwezhIcons.Settings, "Settings"),
    )
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = { NwezhTabBar(tabs, tab, { tab = it }) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text("Nwezh catalog", style = MaterialTheme.typography.headlineMedium)
            Controls(mode, onMode, rtl, onRtl)
            ColorSection()
            ShapeSection()
            TypeSection()
            ComponentSection()
        }
    }
}

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        content()
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun Controls(
    mode: ThemeMode,
    onMode: (ThemeMode) -> Unit,
    rtl: Boolean,
    onRtl: (Boolean) -> Unit
) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ThemeMode.entries.forEach {
            FilterChip(selected = mode == it, onClick = { onMode(it) }, label = { Text(it.name) })
        }
        FilterChip(selected = rtl, onClick = { onRtl(!rtl) }, label = { Text("RTL") })
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun ColorSection() = Section("Color roles") {
    val c = MaterialTheme.colorScheme
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        c.roles().forEach { (name, bg, fg) -> Swatch(name, bg, fg) }
    }
}

private fun ColorScheme.roles(): List<Triple<String, Color, Color>> = listOf(
    Triple("primary", primary, onPrimary),
    Triple("primaryContainer", primaryContainer, onPrimaryContainer),
    Triple("secondary", secondary, onSecondary),
    Triple("secondaryContainer", secondaryContainer, onSecondaryContainer),
    Triple("tertiary", tertiary, onTertiary),
    Triple("tertiaryContainer", tertiaryContainer, onTertiaryContainer),
    Triple("error", error, onError),
    Triple("errorContainer", errorContainer, onErrorContainer),
    Triple("surface", surface, onSurface),
    Triple("surfaceDim", surfaceDim, onSurface),
    Triple("containerLowest", surfaceContainerLowest, onSurface),
    Triple("containerLow", surfaceContainerLow, onSurface),
    Triple("container", surfaceContainer, onSurface),
    Triple("containerHigh", surfaceContainerHigh, onSurface),
    Triple("containerHighest", surfaceContainerHighest, onSurface),
    Triple("inverseSurface", inverseSurface, inverseOnSurface),
)

@Composable
private fun Swatch(name: String, bg: Color, fg: Color) {
    Box(
        modifier = Modifier
            .size(width = 104.dp, height = 56.dp)
            .clip(MaterialTheme.shapes.small)
            .background(bg),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            name,
            color = fg,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun ShapeSection() = Section("Shapes") {
    val s = MaterialTheme.shapes
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ShapeChip("extraSmall", s.extraSmall)
        ShapeChip("small", s.small)
        ShapeChip("medium", s.medium)
        ShapeChip("large", s.large)
        ShapeChip("extraLarge", s.extraLarge)
        ShapeChip("pill", NwezhShapes.Pill)
        ShapeChip("weekDone", NwezhShapes.WeekDayDone)
        ShapeChip("qibla", NwezhShapes.QiblaAligned)
        ShapeChip("circle", CircleShape)
    }
}

@Composable
private fun ShapeChip(name: String, shape: Shape) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(shape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            name,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun TypeSection() = Section("Type") {
    val t = MaterialTheme.typography
    Text("Today", style = t.displaySmall)
    Text("Next prayer in 1h 24m", style = t.titleLarge)
    Text("Fajr  Sunrise  Dhuhr  Asr  Maghrib  Isha", style = t.bodyLarge)
    Text("مواقيت الصلاة • القبلة • القرآن", style = t.titleLarge)
    Text("بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ", style = quranTextStyle())
    Text("ٱلْحَمْدُ لِلَّهِ رَبِّ ٱلْعَٰلَمِينَ", style = quranTextStyle())
}

@Composable
private fun ComponentSection() = Section("Components") {
    var sheet by rememberSaveable { mutableStateOf(false) }
    var on by rememberSaveable { mutableStateOf(true) }
    var minutes by rememberSaveable { mutableIntStateOf(15) }
    val rows = listOf("nav", "switch", "stepper")
    NwezhConnectedColumn(rows) { row, shape ->
        when (row) {
            "nav" -> NwezhNavRow(
                "Calculation method",
                shape,
                onClick = { sheet = true },
                value = "MWL"
            )

            "switch" -> NwezhSwitchRow(
                title = "Fajr adhan",
                shape = shape,
                checked = on,
                onCheckedChange = { on = it },
                leading = {
                    NwezhIcon(
                        if (on) NwezhIcons.NotificationsOn else NwezhIcons.NotificationsOff,
                        contentDescription = null,
                        filled = on,
                    )
                },
            )

            else -> NwezhStepperRow(
                title = "Remind me before",
                shape = shape,
                value = minutes,
                onValueChange = { minutes = it },
                range = 0..60,
                valueLabel = { "$it min" },
            )
        }
    }
    Button(onClick = { sheet = true }) { Text("Open bottom sheet") }
    if (sheet) {
        NwezhBottomSheet(onDismiss = { sheet = false }, title = "Calculation method") {
            Row { Text("Sheet content", style = MaterialTheme.typography.bodyLarge) }
        }
    }
}
