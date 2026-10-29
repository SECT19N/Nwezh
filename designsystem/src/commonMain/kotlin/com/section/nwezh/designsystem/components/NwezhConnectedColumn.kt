package com.section.nwezh.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.section.nwezh.designsystem.theme.NwezhShapes

/**
 * A vertical group of rows with small inner corners and large outer corners. Each item receives its
 * [Shape] from [NwezhShapes.connectedItem]; the [selectedIndex] item becomes a pill.
 */
@Composable
fun <T> NwezhConnectedColumn(
    items: List<T>,
    modifier: Modifier = Modifier,
    selectedIndex: Int? = null,
    itemContent: @Composable (item: T, shape: Shape) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        items.forEachIndexed { index, item ->
            itemContent(
                item,
                NwezhShapes.connectedItem(index, items.size, selected = index == selectedIndex)
            )
        }
    }
}
