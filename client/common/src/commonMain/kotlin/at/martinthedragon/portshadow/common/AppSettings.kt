package at.martinthedragon.portshadow.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.prefs.Preferences

object AppSettings {
    private const val PREFERENCES_NODE = "PortShadowConfig"
    private const val PREF_UI_SCALE = "UIScale"
    private const val PREF_FONT_SCALE = "FontScale"

    private val preferences = Preferences.userRoot().node(PREFERENCES_NODE)

    private val _uiScaleFlow = MutableStateFlow(uiScale)
    val uiScaleFlow = _uiScaleFlow.asStateFlow()

    private val _fontScaleFlow = MutableStateFlow(fontScale)
    val fontScaleFlow = _fontScaleFlow.asStateFlow()

    var uiScale: Float
        get() = preferences.getFloat(PREF_UI_SCALE, 1F)
        set(value) {
            preferences.putFloat(PREF_UI_SCALE, value)
            _uiScaleFlow.value = value
        }

    var fontScale: Float
        get() = preferences.getFloat(PREF_FONT_SCALE, 1F)
        set(value) {
            preferences.putFloat(PREF_FONT_SCALE, value)
            _fontScaleFlow.value = value
        }
}
