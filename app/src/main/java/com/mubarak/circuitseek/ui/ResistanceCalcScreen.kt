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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.circuitseek.R
import com.mubarak.circuitseek.ui.theme.CircuitSeekTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onDrawer: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            CircuitSeekTopAppBar(modifier = modifier){
                onDrawer()
            }
        }
    ) { innerPadding ->
        ResistanceCalcScreen(modifier = Modifier.padding(innerPadding))
    }
}
@Composable
fun ResistanceCalcScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var sourceVolt by remember { mutableStateOf("12.0") }
    var componentVolt by remember { mutableStateOf("2.0") }
    var componentAmp by remember { mutableStateOf("20.0") }
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
            value = sourceVolt,
            label = { Text(text = "Source Volt") },
            placeholder = { Text(text = "Sources Volt") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                it.toFloatOrNull()?.let {
                    sourceVolt = it.toString()
                }
            }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = componentVolt,
                label = { Text(text = "Component Volt") },
                placeholder = { Text(text = "A volt that the component needs") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    it.toFloatOrNull()?.let {
                        componentVolt = it.toString()
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f)

            )

            OutlinedTextField(
                value = componentAmp,
                label = { Text(text = "Component (Milli Amps)") },
                placeholder = { Text(text = "A milli amps that the component needs") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    it.toFloatOrNull()?.let {
                        componentAmp = it.toString()
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f)

            )
        }

        Button(onClick = {

            if (sourceVolt.toFloatOrNull() != null && componentVolt.toFloatOrNull() != null && componentAmp.toFloatOrNull() != null) {
                val droppedVolt = sourceVolt.toFloat() - componentVolt.toFloat()
                val componentRequiredAmp = componentAmp.toFloat()
                val finalResult = droppedVolt / (componentRequiredAmp / 1000)
                Toast.makeText(
                    context,
                    " Use the Resistor $finalResult Ω or above",
                    Toast.LENGTH_LONG
                ).show()
            }

        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Calculate")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CircuitSeekTopAppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = null
                )
            }
        }, modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarPreview() {
    CircuitSeekTheme {
        CircuitSeekTopAppBar{}
    }
}

@Preview(showBackground = true)
@Composable
private fun CircuitSeekAppPreview() {
    CircuitSeekTheme {
        ResistanceCalcScreen()
    }
}