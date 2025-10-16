package com.example.appcitaexpress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.example.appcitaexpress.ui.screen.homeui.HomeScreen
import com.example.appcitaexpress.ui.theme.AppCitaExpressTheme
import com.example.appcitaexpress.ui.theme.GreenMain
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val systemUiController = rememberSystemUiController()
            val colorBackground = GreenMain

            AppCitaExpressTheme {
//                SideEffect {
//                    systemUiController.setStatusBarColor(
//                        color = Color.Tra,
//                        darkIcons = false
//                    )
//                }
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}