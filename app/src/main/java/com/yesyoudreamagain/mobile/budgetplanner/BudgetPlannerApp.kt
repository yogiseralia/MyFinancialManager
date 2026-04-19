package com.yesyoudreamagain.mobile.budgetplanner

import android.app.Application
import com.yesyoudreamagain.mobile.budgetplanner.di.AppComponent
import com.yesyoudreamagain.mobile.budgetplanner.di.DaggerAppComponent

class BudgetPlannerApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}
