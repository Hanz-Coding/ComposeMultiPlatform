package hanz.coding.composemp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import hanz.coding.composemp.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "ComposeMP",
        ) {
            App(
                batteryManager = remember { BatteryManager() }
            )
        }
    }
}