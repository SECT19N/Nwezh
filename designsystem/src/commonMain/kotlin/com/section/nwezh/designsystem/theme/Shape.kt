package com.section.nwezh.designsystem.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

internal val NwezhMaterialShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    largeIncreased = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp),
    extraLargeIncreased = RoundedCornerShape(32.dp),
    extraExtraLarge = RoundedCornerShape(48.dp),
)

/** Shapes from the design spec that are not part of the Material shape scale. */
object NwezhShapes {
    private val ConnectedInner = 6.dp
    private val ConnectedOuter = 24.dp

    /** Selected or next item in a connected group. */
    val Pill = RoundedCornerShape(ConnectedOuter)

    /** Completed day in the week strip. Other days are circles. */
    val WeekDayDone = RoundedCornerShape(14.dp)

    /** The Qibla dial when aligned: a circle morphed to a rounded square. */
    val QiblaAligned = RoundedCornerShape(percent = 38)

    /**
     * Shape for the item at [index] of a connected group of [count] items: large outer corners at
     * the top of the first item and the bottom of the last, small corners everywhere else. A selected
     * item becomes a [Pill].
     */
    fun connectedItem(index: Int, count: Int, selected: Boolean = false): RoundedCornerShape {
        if (selected) return Pill
        val outer = CornerSize(ConnectedOuter)
        val inner = CornerSize(ConnectedInner)
        return RoundedCornerShape(
            topStart = if (index == 0) outer else inner,
            topEnd = if (index == 0) outer else inner,
            bottomEnd = if (index == count - 1) outer else inner,
            bottomStart = if (index == count - 1) outer else inner,
        )
    }
}
