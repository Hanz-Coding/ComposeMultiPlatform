package hanz.coding.composemp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import hanz.coding.composemp.di.initKoin
import hanz.coding.composemp.network.InsultCensorClient
import hanz.coding.composemp.network.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "ComposeMP",
        ) {
            App(
                client = remember {
                    InsultCensorClient(createHttpClient(OkHttp.create()))
                },
                batteryManager = remember { BatteryManager() }
            )
        }
    }
}