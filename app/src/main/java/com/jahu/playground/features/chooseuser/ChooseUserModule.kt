package com.jahu.playground.features.chooseuser

import com.jahu.playground.di.FeatureScope
import com.jahu.playground.usecases.users.GetActualUserUseCase
import com.jahu.playground.usecases.users.GetUsersUseCase
import com.jahu.playground.usecases.users.SetActualUserUseCase
import dagger.Module
import dagger.Provides

@Module
class ChooseUserModule(
        private val view: ChooseUserContract.View
) {

    @FeatureScope
    @Provides
    fun providePresenter(getActualUserUseCase: GetActualUserUseCase,
                         getUsersUseCase: GetUsersUseCase,
                         setActualUserUseCase: SetActualUserUseCase): ChooseUserContract.Presenter
            = ChooseUserPresenter(view, getActualUserUseCase, getUsersUseCase, setActualUserUseCase)

}
