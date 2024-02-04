package org.michaelbel.movies.ui.appicon

import androidx.annotation.DrawableRes
import org.michaelbel.movies.ui.R

sealed class IconAlias(
    val key: String,
    @DrawableRes val iconRes: Int
) {

    data object Red: IconAlias(
        key = RED_ICON_KEY,
        iconRes = R.drawable.ic_launcher_icon_red
    )

    data object Purple: IconAlias(
        key = PURPLE_ICON_KEY,
        iconRes = R.drawable.ic_launcher_icon_purple
    )

    data object Brown: IconAlias(
        key = BROWN_ICON_KEY,
        iconRes = R.drawable.ic_launcher_icon_brown
    )

    data object Amoled: IconAlias(
        key = AMOLED_ICON_KEY,
        iconRes = R.drawable.ic_launcher_icon_amoled
    )

    companion object {
        private const val RED_ICON_KEY = "RedIcon"
        private const val PURPLE_ICON_KEY = "PurpleIcon"
        private const val BROWN_ICON_KEY = "BrownIcon"
        private const val AMOLED_ICON_KEY = "AmoledIcon"

        val VALUES = listOf(
            Red,
            Purple,
            Brown,
            Amoled
        )
    }
}