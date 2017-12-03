package com.jahu.playground.features.settings

import com.jahu.playground.di.FeatureScope
import com.jahu.playground.usecases.users.SetActualUserUseCase
import dagger.Module
import dagger.Provides

@Module
class SettingsModule(
        private val view: SettingsContract.View
) {

    @FeatureScope
    @Provides
    fun providePresenter(setActualUserUseCase: SetActualUserUseCase): SettingsContract.Presenter
            = SettingsPresenter(view, setActualUserUseCase)

}
