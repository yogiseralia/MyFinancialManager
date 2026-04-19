package com.yesyoudreamagain.mobile.budgetplanner.dashboard.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.yesyoudreamagain.mobile.budgetplanner.core.mvi.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : BaseViewModel<DashboardContract.Event, DashboardContract.State, DashboardContract.Effect>() {

    override fun createInitialState(): DashboardContract.State {
        return DashboardContract.State()
    }

    init {
        fetchExchangeRate()
    }

    private fun fetchExchangeRate() {
        firestore.collection("metadata").document("exchange_rates")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    setEffect { DashboardContract.Effect.ShowError("Failed to fetch exchange rate") }
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val rate = snapshot.getDouble("aed_to_inr")
                    if (rate != null) {
                        setState { copy(exchangeRateAedToInr = rate) }
                    }
                }
            }
    }

    override fun handleEvent(event: DashboardContract.Event) {
        when (event) {
            is DashboardContract.Event.ToggleCurrency -> {
                setState { copy(isAedSelected = !isAedSelected) }
            }
        }
    }
}
