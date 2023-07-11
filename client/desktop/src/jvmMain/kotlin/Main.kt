import androidx.compose.material.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.configureSwingGlobalsForCompose
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import at.martinthedragon.portshadow.common.App
import at.martinthedragon.portshadow.common.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    configureSwingGlobalsForCompose()
    application {
        MaterialTheme(
            colors = AppTheme.colors.material
        ) {
            Window(onCloseRequest = ::exitApplication, undecorated = true) {
                App(this@application::exitApplication)
            }
        }
    }
}
