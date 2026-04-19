package com.yesyoudreamagain.mobile.budgetplanner.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yesyoudreamagain.mobile.budgetplanner.dashboard.presentation.viewmodel.DashboardViewModel
import com.yesyoudreamagain.mobile.budgetplanner.renttracker.presentation.viewmodel.RentTrackerViewModel
import com.yesyoudreamagain.mobile.budgetplanner.transactions.presentation.viewmodel.TransactionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RentTrackerViewModel::class)
    abstract fun bindRentTrackerViewModel(viewModel: RentTrackerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    abstract fun bindTransactionsViewModel(viewModel: TransactionsViewModel): ViewModel
}
