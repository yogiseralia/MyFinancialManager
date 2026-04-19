package com.yesyoudreamagain.mobile.budgetplanner.dashboard.presentation.viewmodel

import com.yesyoudreamagain.mobile.budgetplanner.core.mvi.ViewEffect
import com.yesyoudreamagain.mobile.budgetplanner.core.mvi.ViewEvent
import com.yesyoudreamagain.mobile.budgetplanner.core.mvi.ViewState

class DashboardContract {
    sealed class Event : ViewEvent {
        object ToggleCurrency : Event()
    }

    data class State(
        val isAedSelected: Boolean = true,
        val exchangeRateAedToInr: Double = 22.5,
        val uaeAssetsAed: Double = 10000.0,
        val indiaAssetsInr: Double = 500000.0
    ) : ViewState

    sealed class Effect : ViewEffect {
        data class ShowError(val message: String) : Effect()
    }
}
