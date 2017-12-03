package com.jahu.playground.di

import com.jahu.playground.features.gamesetup.GameSetupFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class])
interface AppComponent {

    fun inject(fragment: GameSetupFragment)

}
