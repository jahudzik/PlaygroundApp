package com.jahu.playground.features.edituser

import com.jahu.playground.di.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [EditUserModule::class])
interface EditUserComponent {

    fun inject(activity: EditUserActivity)

}
