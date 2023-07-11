package at.martinthedragon.portshadow.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState

@Composable
fun SettingsDialog(onCloseRequest: () -> Unit, vararg compositionValues: ProvidedValue<*>) {
    Dialog(onCloseRequest, undecorated = true, state = rememberDialogState(position = WindowPosition(Alignment.Center))) {
        CompositionLocalProvider(values = compositionValues) {
            Surface(Modifier.fillMaxSize()) {
                Column {
                    WindowBar(onCloseRequest)
                    Row {
                        Text("App scale")
                        AppScaleSetting()
                    }
                }
            }
        }
    }
}

@Composable
fun AppScaleSetting() {
    var options by remember { mutableStateOf(listOf(
        DropDownOption(0.5F, "50%"),
        DropDownOption(0.75F, "75%"),
        DropDownOption(1F, "100%"),
        DropDownOption(1.25F, "125%"),
        DropDownOption(1.5F, "150%"),
        DropDownOption(2F, "200%"),
        DropDownOption(2.5F, "250%"),
    )) }

    var selectedScale by remember {
        val currentScale = AppSettings.uiScale
        val currentOption = options.firstOrNull { it.value == currentScale } ?: run {
            val newOption = DropDownOption(currentScale, "${(currentScale * 100).toInt()}%")
            val updatedOptions = options.toMutableList()
            updatedOptions += newOption
            updatedOptions.sortBy(DropDownOption<Float>::value)
            options = updatedOptions.toList()
            newOption
        }
        mutableStateOf(currentOption)
    }

    SettingsDropdownMenu(options, selectedScale) {
        selectedScale = it
        AppSettings.uiScale = it.value
    }
}

@Composable
private fun <T> SettingsDropdownMenu(options: List<DropDownOption<T>>, currentOption: DropDownOption<T>, onSelectOption: (DropDownOption<T>) -> Unit) {
    Box {
        var expanded by remember { mutableStateOf(false) }

        OutlinedButton(
            onClick = { expanded = true }
        ) {
            Text(currentOption.name, maxLines = 1)
        }
        DropdownMenu(expanded, { expanded = false }) {
            for (option in options)
                DropdownMenuItem({ expanded = false; onSelectOption(option) }) {
                    Text(option.name)
                }
        }
    }
}

private data class DropDownOption<T>(val value: T, val name: String)
