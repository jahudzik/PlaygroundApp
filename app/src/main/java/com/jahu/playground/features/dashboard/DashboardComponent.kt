package com.jahu.playground.features.dashboard

import com.jahu.playground.di.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [DashboardModule::class])
interface DashboardComponent {

    fun inject(activity: DashboardActivity)

}
