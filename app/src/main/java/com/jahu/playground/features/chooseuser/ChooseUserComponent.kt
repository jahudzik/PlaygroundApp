package com.jahu.playground.features.chooseuser

import com.jahu.playground.di.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [ChooseUserModule::class])
interface ChooseUserComponent {

    fun inject(activity: ChooseUserActivity)

}
