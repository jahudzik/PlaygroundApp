package com.jahu.playground.di

import com.jahu.playground.features.gamesetup.GameSetupComponent
import com.jahu.playground.features.gamesetup.GameSetupModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class])
interface AppComponent {

    fun plus(module: GameSetupModule): GameSetupComponent

}
