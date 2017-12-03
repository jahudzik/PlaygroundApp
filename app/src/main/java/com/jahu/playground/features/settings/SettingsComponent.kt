package com.jahu.playground.features.settings

import com.jahu.playground.di.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {

    fun inject(fragment: SettingsFragment)

}
