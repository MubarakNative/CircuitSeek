package com.mubarak.circuitseek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mubarak.circuitseek.ui.HomeScreen
import com.mubarak.circuitseek.ui.theme.CircuitSeekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircuitSeekTheme {
              HomeScreen()
            }
        }
    }
}
