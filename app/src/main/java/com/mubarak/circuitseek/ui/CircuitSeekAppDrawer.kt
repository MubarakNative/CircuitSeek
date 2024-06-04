package com.mubarak.circuitseek.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.circuitseek.R
import com.mubarak.circuitseek.Resistance
import com.mubarak.circuitseek.Watts
import com.mubarak.circuitseek.ui.theme.CircuitSeekTheme

@Composable
fun CircuitSeekAppDrawer(
    modifier: Modifier = Modifier,
    currentDestination: Any,
    navigateToResistance: () -> Unit,
    navigateToWatts: () -> Unit,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet(
        modifier = modifier
    ) {
        NavigationHeader()
        NavigationDrawerItem(
            label = {
                Text(text = stringResource(R.string.resistance))
            },
            selected = currentDestination == Resistance,
            onClick = {
                navigateToResistance(); closeDrawer()
            },
            icon = {
                Icon(
                    painterResource(id = R.drawable.resistor_icon24px),
                    contentDescription = null
                )
            })

        NavigationDrawerItem(
            label = {
                Text(text = stringResource(R.string.watts_calc))
            },
            selected = currentDestination == Watts,
            onClick = {
                navigateToWatts(); closeDrawer()
            },
            icon = { Icon(painterResource(id = R.drawable.power_icon24px), contentDescription = null) }
        )
    }
}

@Composable
fun NavigationHeader(modifier: Modifier = Modifier) {

    Box(modifier = modifier.padding(horizontal = 28.dp, vertical = 24.dp)) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = modifier,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NavHeaderPreview() {
    CircuitSeekTheme {
        CircuitSeekAppDrawer(
            navigateToWatts = {},
            navigateToResistance = {},
            closeDrawer = {},
            currentDestination = Resistance
        )
    }
}