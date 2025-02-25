package hanz.coding.composemp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import hanz.coding.composemp.datastore.DATA_STORE_FILE_NAME
import hanz.coding.composemp.datastore.createDataStore
import hanz.coding.composemp.di.initKoin
import hanz.coding.composemp.network.InsultCensorClient
import hanz.coding.composemp.network.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

fun main() {
    initKoin()
    val prefs = createDataStore {
        DATA_STORE_FILE_NAME
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "ComposeMP",
        ) {
            App(
                prefs = remember {
                    prefs
                },
                client = remember {
                    InsultCensorClient(createHttpClient(OkHttp.create()))
                },
                batteryManager = remember { BatteryManager() }
            )
        }
    }
}