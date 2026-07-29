package com.example.enmanuel_gomez_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.enmanuel_gomez_ap2_p2.navigation.AppNavHost
import com.example.enmanuel_gomez_ap2_p2.ui.theme.Enmanuel_Gomez_Ap2_p2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Enmanuel_Gomez_Ap2_p2Theme {
                AppNavHost()
            }
        }
    }
}
