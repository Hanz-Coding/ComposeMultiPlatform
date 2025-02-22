package hanz.coding.composemp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import composemp.composeapp.generated.resources.Res
import composemp.composeapp.generated.resources.hello_duong
import composemp.composeapp.generated.resources.logo
import hanz.coding.composemp.dependencies.MyViewModel
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
    batteryManager: BatteryManager
) {
    MaterialTheme {

        KoinContext {
            NavHost(
                navController = rememberNavController(),
                startDestination = "home"
            ){
                composable(route = "home"){
                    val viewModel = koinViewModel<MyViewModel>()
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Image(painterResource(Res.drawable.logo), contentDescription = null)
                            Text(text = stringResource(Res.string.hello_duong))
                            Text(text = "Battery Level ${batteryManager.getBatteryLevel()}")
                            Text(text = viewModel.getHelloWorldString())
                        }
                    }
                }
            }
        }

    }
}