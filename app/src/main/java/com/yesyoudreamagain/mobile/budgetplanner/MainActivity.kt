package com.yesyoudreamagain.mobile.budgetplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.yesyoudreamagain.mobile.budgetplanner.dashboard.presentation.ui.DashboardScreen
import com.yesyoudreamagain.mobile.budgetplanner.dashboard.presentation.viewmodel.DashboardViewModel
import com.yesyoudreamagain.mobile.budgetplanner.renttracker.presentation.ui.RentTrackerScreen
import com.yesyoudreamagain.mobile.budgetplanner.renttracker.presentation.viewmodel.RentTrackerViewModel
import com.yesyoudreamagain.mobile.budgetplanner.transactions.presentation.ui.TransactionsScreen
import com.yesyoudreamagain.mobile.budgetplanner.transactions.presentation.viewmodel.TransactionsViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val dashboardViewModel: DashboardViewModel by viewModels { viewModelFactory }
    private val rentTrackerViewModel: RentTrackerViewModel by viewModels { viewModelFactory }
    private val transactionsViewModel: TransactionsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BudgetPlannerApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        DashboardScreen(viewModel = dashboardViewModel)
                        RentTrackerScreen(viewModel = rentTrackerViewModel)
                        Box(modifier = Modifier.weight(1f)) {
                            TransactionsScreen(viewModel = transactionsViewModel)
                        }
                    }
                }
            }
        }
    }
}
