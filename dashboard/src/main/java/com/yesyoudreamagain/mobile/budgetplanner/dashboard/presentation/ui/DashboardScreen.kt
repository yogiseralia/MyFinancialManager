package com.yesyoudreamagain.mobile.budgetplanner.dashboard.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yesyoudreamagain.mobile.budgetplanner.dashboard.presentation.viewmodel.DashboardContract
import com.yesyoudreamagain.mobile.budgetplanner.dashboard.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Budget Planner Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "INR")
            Switch(
                checked = state.isAedSelected,
                onCheckedChange = { viewModel.setEvent(DashboardContract.Event.ToggleCurrency) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(text = "AED")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // UAE Assets Card
        val uaeDisplayValue = if (state.isAedSelected) {
            state.uaeAssetsAed
        } else {
            state.uaeAssetsAed * state.exchangeRateAedToInr
        }
        val uaeCurrencyLabel = if (state.isAedSelected) "AED" else "INR"

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "UAE Assets", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "$uaeDisplayValue $uaeCurrencyLabel",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        // India Assets Card
        val indiaDisplayValue = if (state.isAedSelected) {
            state.indiaAssetsInr / state.exchangeRateAedToInr
        } else {
            state.indiaAssetsInr
        }
        val indiaCurrencyLabel = if (state.isAedSelected) "AED" else "INR"

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "India Assets", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "$indiaDisplayValue $indiaCurrencyLabel",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}
