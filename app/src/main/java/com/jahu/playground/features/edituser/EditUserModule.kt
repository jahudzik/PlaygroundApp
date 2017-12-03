package com.jahu.playground.features.edituser

import com.jahu.playground.di.FeatureScope
import com.jahu.playground.usecases.users.AddUserUseCase
import com.jahu.playground.usecases.users.GetActualUserUseCase
import com.jahu.playground.usecases.users.UpdateUserUserCase
import dagger.Module
import dagger.Provides

@Module
class EditUserModule(
        private val view: EditUserContract.View,
        private val mode: EditUserContract.Mode
) {

    @FeatureScope
    @Provides
    fun providePresenter(addUserUseCase: AddUserUseCase,
                         getActualUserUseCase: GetActualUserUseCase,
                         updateUserUserCase: UpdateUserUserCase): EditUserContract.Presenter
            = EditUserPresenter(mode, view, addUserUseCase, getActualUserUseCase, updateUserUserCase)

}
