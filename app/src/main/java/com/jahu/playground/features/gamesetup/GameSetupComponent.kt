package com.jahu.playground.features.gamesetup

import com.jahu.playground.di.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [GameSetupModule::class])
interface GameSetupComponent {

    fun inject(fragment: GameSetupFragment)

}
