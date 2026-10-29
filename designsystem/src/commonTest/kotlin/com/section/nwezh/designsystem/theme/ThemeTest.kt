package com.section.nwezh.designsystem.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ThemeTest {
    @Test
    fun themeModeResolvesDark() {
        assertTrue(ThemeMode.System.isDark(systemDark = true))
        assertFalse(ThemeMode.System.isDark(systemDark = false))
        assertFalse(ThemeMode.Light.isDark(systemDark = true))
        assertTrue(ThemeMode.Dark.isDark(systemDark = false))
    }

    @Test
    fun connectedGroupRoundsOnlyOuterCorners() {
        val first = NwezhShapes.connectedItem(0, 3)
        val middle = NwezhShapes.connectedItem(1, 3)
        val last = NwezhShapes.connectedItem(2, 3)
        assertEquals(CornerSize(24.dp), first.topStart)
        assertEquals(CornerSize(6.dp), first.bottomStart)
        assertEquals(CornerSize(6.dp), middle.topStart)
        assertEquals(CornerSize(6.dp), middle.bottomEnd)
        assertEquals(CornerSize(6.dp), last.topEnd)
        assertEquals(CornerSize(24.dp), last.bottomEnd)
    }

    @Test
    fun singleItemGroupIsFullyRoundedAndSelectedIsPill() {
        val only = NwezhShapes.connectedItem(0, 1)
        assertEquals(CornerSize(24.dp), only.topStart)
        assertEquals(CornerSize(24.dp), only.bottomEnd)
        assertEquals(NwezhShapes.Pill, NwezhShapes.connectedItem(1, 3, selected = true))
    }
}
