package com.jahu.playground.features.dashboard

import com.jahu.playground.di.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class DashboardModule(
        private val view: DashboardContract.View
) {

    @FeatureScope
    @Provides
    fun providePresenter(): DashboardContract.Presenter = DashboardPresenter(view)

}
