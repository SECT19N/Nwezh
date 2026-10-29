package com.section.nwezh.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.section.nwezh.designsystem.icons.NwezhIcon
import com.section.nwezh.designsystem.icons.NwezhIcons

/** Base row: [title] and optional [subtitle], with a [leading] icon and a [trailing] control. */
@Composable
fun NwezhListRow(
    title: String,
    shape: Shape,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    rowModifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth().clip(shape),
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Row(
            modifier = rowModifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            leading?.invoke()
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (subtitle != null) {
                    Text(
                        subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            trailing?.invoke()
        }
    }
}

/** Navigates somewhere: optional current [value] and a chevron that mirrors in RTL. */
@Composable
fun NwezhNavRow(
    title: String,
    shape: Shape,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    value: String? = null,
    leading: @Composable (() -> Unit)? = null,
) {
    val mirror = LocalLayoutDirection.current == LayoutDirection.Rtl
    NwezhListRow(
        title = title,
        shape = shape,
        modifier = modifier,
        subtitle = subtitle,
        leading = leading,
        rowModifier = Modifier.clickable(role = Role.Button, onClick = onClick),
        trailing = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (value != null) {
                    Text(
                        value,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                NwezhIcon(
                    NwezhIcons.ChevronRight,
                    contentDescription = null,
                    modifier = Modifier.graphicsLayer { scaleX = if (mirror) -1f else 1f },
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
    )
}

/** A setting that is on or off. The whole row toggles. */
@Composable
fun NwezhSwitchRow(
    title: String,
    shape: Shape,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
) {
    NwezhListRow(
        title = title,
        shape = shape,
        modifier = modifier,
        subtitle = subtitle,
        leading = leading,
        rowModifier = Modifier.toggleable(
            value = checked,
            role = Role.Switch,
            onValueChange = onCheckedChange
        ),
        trailing = { Switch(checked = checked, onCheckedChange = null) },
    )
}

/** A whole number with minus and plus buttons, kept within [range]. */
@Composable
fun NwezhStepperRow(
    title: String,
    shape: Shape,
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    valueLabel: (Int) -> String = { it.toString() },
) {
    NwezhListRow(
        title = title,
        shape = shape,
        modifier = modifier,
        subtitle = subtitle,
        trailing = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(
                    onClick = { onValueChange((value - 1).coerceIn(range)) },
                    enabled = value > range.first,
                ) { Text("\u2212") }
                Text(
                    valueLabel(value),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                TextButton(
                    onClick = { onValueChange((value + 1).coerceIn(range)) },
                    enabled = value < range.last,
                ) { Text("+") }
            }
        },
    )
}
