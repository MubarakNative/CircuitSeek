package com.mubarak.circuitseek.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.circuitseek.ui.theme.CircuitSeekTheme

@Composable
fun WattsCalcScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var voltage by remember { mutableStateOf("3.3") }
    var ampere by remember { mutableStateOf("200") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = voltage,
            label = { Text(text = "Volt") },
            placeholder = { Text(text = "Volt") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                it.toFloatOrNull()?.let {
                    voltage = it.toString()
                }
            }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = ampere,
                label = { Text(text = "Current (Milli Amps)") },
                placeholder = { Text(text = "Enter the milli amps") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    it.toFloatOrNull()?.let {
                        ampere = it.toString()
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()

            )
        }

        Button(onClick = {

            if (voltage.toFloatOrNull() != null && ampere.toFloatOrNull() != null) {
                val amps = ampere.toFloat()
                val volt = voltage.toFloat()

                val finalResult = (amps / 1000) * volt
                Toast.makeText(
                    context,
                    "Watts is $finalResult",
                    Toast.LENGTH_LONG
                ).show()
            }

        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Find watts")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WattsScreenPreview() {
    CircuitSeekTheme {
        WattsCalcScreen()
    }
}