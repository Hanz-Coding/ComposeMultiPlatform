package hanz.coding.composemp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import hanz.coding.composemp.di.initKoin
import hanz.coding.composemp.network.InsultCensorClient
import hanz.coding.composemp.network.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App(
        client = remember {
            InsultCensorClient(createHttpClient(Darwin.create()))
        },
        batteryManager = remember { BatteryManager() }
    )
}