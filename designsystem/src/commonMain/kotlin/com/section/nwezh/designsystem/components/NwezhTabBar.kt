package com.section.nwezh.designsystem.components

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.section.nwezh.designsystem.icons.NwezhIcon

class NwezhTab(val icon: NwezhIcon, val label: String)

/** Bottom tab bar. The selected tab shows its filled icon. */
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NwezhTabBar(
    tabs: List<NwezhTab>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    ShortNavigationBar(modifier = modifier) {
        tabs.forEachIndexed { index, tab ->
            ShortNavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onSelect(index) },
                icon = {
                    NwezhIcon(
                        tab.icon,
                        contentDescription = null,
                        filled = index == selectedIndex
                    )
                },
                label = { Text(tab.label) },
            )
        }
    }
}
