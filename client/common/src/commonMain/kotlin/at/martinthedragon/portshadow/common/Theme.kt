package at.martinthedragon.portshadow.common

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

object AppTheme {
    val colors = Colors()

    data class Colors(
        val backgroundDark: Color = Color(0xFF0D1117),
        val backgroundMedium: Color = Color(0xFF232B38),
        val backgroundLight: Color = Color(0xFF7A8596),
        val material: androidx.compose.material.Colors = darkColors(
            background = backgroundDark,
            surface = backgroundMedium,
            primary = Color.White
        )
    )
}
