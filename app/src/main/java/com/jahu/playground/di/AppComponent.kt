package com.jahu.playground.di

import com.jahu.playground.features.chooseuser.ChooseUserComponent
import com.jahu.playground.features.chooseuser.ChooseUserModule
import com.jahu.playground.features.dashboard.DashboardComponent
import com.jahu.playground.features.dashboard.DashboardModule
import com.jahu.playground.features.edituser.EditUserComponent
import com.jahu.playground.features.edituser.EditUserModule
import com.jahu.playground.features.game.GameComponent
import com.jahu.playground.features.game.GameModule
import com.jahu.playground.features.gamesetup.GameSetupComponent
import com.jahu.playground.features.gamesetup.GameSetupModule
import com.jahu.playground.features.leaderboard.LeaderboardComponent
import com.jahu.playground.features.leaderboard.LeaderboardModule
import com.jahu.playground.features.settings.SettingsComponent
import com.jahu.playground.features.settings.SettingsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class])
interface AppComponent {

    fun plus(module: ChooseUserModule): ChooseUserComponent

    fun plus(module: DashboardModule): DashboardComponent

    fun plus(module: EditUserModule): EditUserComponent

    fun plus(module: GameModule): GameComponent

    fun plus(module: GameSetupModule): GameSetupComponent

    fun plus(module: LeaderboardModule): LeaderboardComponent

    fun plus(module: SettingsModule): SettingsComponent

}
