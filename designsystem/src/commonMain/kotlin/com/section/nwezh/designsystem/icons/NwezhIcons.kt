package com.section.nwezh.designsystem.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.section.nwezh.designsystem.resources.Res
import com.section.nwezh.designsystem.resources.ic_calendar_month
import com.section.nwezh.designsystem.resources.ic_calendar_month_filled
import com.section.nwezh.designsystem.resources.ic_check_circle
import com.section.nwezh.designsystem.resources.ic_check_circle_filled
import com.section.nwezh.designsystem.resources.ic_chevron_right
import com.section.nwezh.designsystem.resources.ic_explore
import com.section.nwezh.designsystem.resources.ic_explore_filled
import com.section.nwezh.designsystem.resources.ic_home
import com.section.nwezh.designsystem.resources.ic_home_filled
import com.section.nwezh.designsystem.resources.ic_menu_book
import com.section.nwezh.designsystem.resources.ic_menu_book_filled
import com.section.nwezh.designsystem.resources.ic_notifications_active
import com.section.nwezh.designsystem.resources.ic_notifications_active_filled
import com.section.nwezh.designsystem.resources.ic_notifications_off
import com.section.nwezh.designsystem.resources.ic_notifications_off_filled
import com.section.nwezh.designsystem.resources.ic_radio_button_unchecked
import com.section.nwezh.designsystem.resources.ic_settings
import com.section.nwezh.designsystem.resources.ic_settings_filled
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/** A Material Symbols Rounded glyph in its outlined and filled (FILL 1) forms. */
class NwezhIcon(val outlined: DrawableResource, val filled: DrawableResource = outlined)

object NwezhIcons {
    val Home = NwezhIcon(Res.drawable.ic_home, Res.drawable.ic_home_filled)
    val Quran = NwezhIcon(Res.drawable.ic_menu_book, Res.drawable.ic_menu_book_filled)
    val Qibla = NwezhIcon(Res.drawable.ic_explore, Res.drawable.ic_explore_filled)
    val Calendar = NwezhIcon(Res.drawable.ic_calendar_month, Res.drawable.ic_calendar_month_filled)
    val Settings = NwezhIcon(Res.drawable.ic_settings, Res.drawable.ic_settings_filled)
    val NotificationsOn =
        NwezhIcon(Res.drawable.ic_notifications_active, Res.drawable.ic_notifications_active_filled)
    val NotificationsOff =
        NwezhIcon(Res.drawable.ic_notifications_off, Res.drawable.ic_notifications_off_filled)
    val CheckCircle = NwezhIcon(Res.drawable.ic_check_circle, Res.drawable.ic_check_circle_filled)
    val Unchecked = NwezhIcon(Res.drawable.ic_radio_button_unchecked)
    val ChevronRight = NwezhIcon(Res.drawable.ic_chevron_right)
}

/** Draws [icon] tinted with [tint], 24dp by default. */
@Composable
fun NwezhIcon(
    icon: NwezhIcon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    filled: Boolean = false,
    tint: Color = LocalContentColor.current,
) {
    Image(
        painter = painterResource(if (filled) icon.filled else icon.outlined),
        contentDescription = contentDescription,
        modifier = modifier.size(24.dp),
        colorFilter = ColorFilter.tint(tint),
    )
}
