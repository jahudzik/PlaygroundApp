package com.jahu.playground.features.leaderboard

import com.jahu.playground.di.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [LeaderboardModule::class])
interface LeaderboardComponent {

    fun inject(fragment: LeaderboardFragment)

}
