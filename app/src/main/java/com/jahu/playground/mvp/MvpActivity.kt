package com.jahu.playground.mvp

import android.app.Activity
import android.os.Bundle
import com.jahu.playground.PlaygroundApplication

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class MvpActivity<P : MvpPresenter> : Activity() {

    protected open lateinit var presenter: P

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
