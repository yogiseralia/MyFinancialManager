package com.yesyoudreamagain.mobile.budgetplanner.renttracker.presentation.viewmodel

import com.yesyoudreamagain.mobile.budgetplanner.core.mvi.BaseViewModel
import com.yesyoudreamagain.mobile.budgetplanner.renttracker.domain.RentLogic
import java.time.LocalDate
import javax.inject.Inject

class RentTrackerViewModel @Inject constructor() :
    BaseViewModel<RentTrackerContract.Event, RentTrackerContract.State, RentTrackerContract.Effect>() {

    override fun createInitialState() = RentTrackerContract.State()

    init {
        setEvent(RentTrackerContract.Event.CalculateRent)
    }

    override fun handleEvent(event: RentTrackerContract.Event) {
        when (event) {
            is RentTrackerContract.Event.CalculateRent -> {
                val today = LocalDate.now()
                val currentRent = RentLogic.calculateCurrentRent(today)
                val daysUntilRenewal = RentLogic.daysUntilNextRenewal(today)

                setState {
                    copy(
                        currentRentAed = currentRent,
                        daysUntilRenewal = daysUntilRenewal
                    )
                }
            }
        }
    }
}
