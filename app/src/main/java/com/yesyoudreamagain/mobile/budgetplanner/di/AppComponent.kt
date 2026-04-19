package com.yesyoudreamagain.mobile.budgetplanner.di

import android.content.Context
import com.yesyoudreamagain.mobile.budgetplanner.MainActivity
import com.yesyoudreamagain.mobile.budgetplanner.core.di.CoreModule
import com.yesyoudreamagain.mobile.budgetplanner.dashboard.di.DashboardModule
import com.yesyoudreamagain.mobile.budgetplanner.transactions.di.TransactionsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CoreModule::class,
    DashboardModule::class,
    TransactionsModule::class,
    AppModule::class,
    ViewModelModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
