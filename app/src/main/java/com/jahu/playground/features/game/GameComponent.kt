package com.jahu.playground.features.game

import com.jahu.playground.di.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [GameModule::class])
interface GameComponent {

    fun inject(activity: GameActivity)

}
