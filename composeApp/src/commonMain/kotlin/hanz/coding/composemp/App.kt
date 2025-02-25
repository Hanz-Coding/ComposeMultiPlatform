package hanz.coding.composemp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import composemp.composeapp.generated.resources.Res
import composemp.composeapp.generated.resources.hello_duong
import composemp.composeapp.generated.resources.logo
import hanz.coding.composemp.dependencies.MyViewModel
import hanz.coding.composemp.network.InsultCensorClient
import hanz.coding.composemp.utils.NetworkError
import hanz.coding.composemp.utils.onError
import hanz.coding.composemp.utils.onSuccess
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App(
    prefs: DataStore<Preferences>,
    client: InsultCensorClient,
    batteryManager: BatteryManager
) {
    MaterialTheme {

        KoinContext {
            NavHost(
                navController = rememberNavController(),
                startDestination = "home"
            ) {
                composable(route = "home") {
                    val viewModel = koinViewModel<MyViewModel>()

                    var censorText by remember {
                        mutableStateOf<String?>(null)
                    }

                    var uncensorText by remember {
                        mutableStateOf("")
                    }
                    var isLoading by remember {
                        mutableStateOf(false)
                    }

                    var errorMessage by remember {
                        mutableStateOf<NetworkError?>(null)
                    }
                    val scope = rememberCoroutineScope()
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Image(painterResource(Res.drawable.logo), contentDescription = null)
                            Text(text = stringResource(Res.string.hello_duong))
                            Text(text = "Battery Level ${batteryManager.getBatteryLevel()}")
                            Text(text = viewModel.getHelloWorldString())

                            //censor

//                            TextField(
//                                value = uncensorText,
//                                onValueChange = { uncensorText = it },
//                                modifier = Modifier.padding(horizontal = 16.dp)
//                                    .fillMaxWidth(),
//                                placeholder = {
//                                    Text("Uncensored text")
//                                }
//                            )
//                            Button(onClick = {
//                                scope.launch {
//                                    isLoading = true
//                                    errorMessage = null
//                                    client.censorWord(uncensorText)
//                                        .onSuccess {
//                                            censorText = it
//                                        }
//                                        .onError {
//                                            errorMessage = it
//                                        }
//                                    isLoading = false
//                                }
//                            }) {
//                                if (isLoading) {
//                                    CircularProgressIndicator(
//                                        modifier = Modifier.size(15.dp),
//                                        strokeWidth = 1.dp,
//                                        color = Color.White
//                                    )
//                                } else {
//                                    Text("Censor")
//                                }
//                            }
//
//                            censorText?.let {
//                                Text(it)
//                            }
//
//                            errorMessage?.let {
//                                Text(it.name)
//                            }

                            // end censor

                            // Datasource
                            val counterKey = intPreferencesKey("counter")
                            val counter by prefs.data
                                .map {
                                    it[counterKey] ?: 0
                                }.collectAsState(0)
                            Text(
                                text = counter.toString(),
                                textAlign = TextAlign.Center,
                                fontSize = 40.sp
                            )
                            Button(onClick = {
                                scope.launch {
                                    prefs.edit { dataStore ->
                                        val counterKey = intPreferencesKey("counter")
                                        dataStore[counterKey] = counter + 1
                                    }
                                }
                            }) {
                                Text("increment")
                            }
                        }
                    }
                }
            }
        }

    }
}