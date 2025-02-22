package hanz.coding.composemp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import hanz.coding.composemp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App(
        batteryManager = remember { BatteryManager() }
    )
}