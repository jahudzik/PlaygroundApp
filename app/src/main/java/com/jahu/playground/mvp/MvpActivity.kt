package com.jahu.playground.mvp

import android.app.Activity
import android.os.Bundle
import com.jahu.playground.PlaygroundApplication
import javax.inject.Inject

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class MvpActivity<P : MvpPresenter> : Activity() {

    @Inject protected lateinit var presenter: P

    protected fun getAppComponent() = (application as PlaygroundApplication).getAppComponent()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.createView()
    }

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }
}
