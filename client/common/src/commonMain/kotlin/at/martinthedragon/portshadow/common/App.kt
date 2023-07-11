package at.martinthedragon.portshadow.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.WindowScope

@Composable
fun WindowScope.App(onCloseRequest: () -> Unit) {
    val uiScale by AppSettings.uiScaleFlow.collectAsState()
    val fontScale by AppSettings.fontScaleFlow.collectAsState()

    val localCompositionValues = buildList<ProvidedValue<*>> {
        add(LocalDensity provides Density(uiScale, fontScale))
    }.toTypedArray()

    CompositionLocalProvider(values = localCompositionValues) {
        DisableSelection {
            Surface(modifier = Modifier.fillMaxSize()) {
                var isSettingsDialogActive by remember { mutableStateOf(false) }
                Column {
                    WindowBar(onCloseRequest)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        IconButton({ isSettingsDialogActive = true }) {
                            Icon(Icons.Outlined.Settings, "Open Settings")
                        }
                    }
                }
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text("Port", fontSize = 2.5F.em)
                        Text("Shadow", fontSize = 2.5F.em, color = Color.Gray)
                    }
                }
                if (isSettingsDialogActive) {
                    SettingsDialog({ isSettingsDialogActive = false }, compositionValues = localCompositionValues)
                }
            }
        }
    }
}

@Composable
fun WindowScope.WindowBar(onCloseRequest: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        WindowDraggableArea(modifier = Modifier.height(32.dp).weight(1F))
        DisableSelection {  }
        IconButton(onClick = onCloseRequest) {
            Icon(Icons.Filled.Close, "Exit Application")
        }
    }
}
