package com.jahu.playground.mvp

import android.app.Activity
import android.os.Bundle

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class MvpActivity<P : MvpPresenter> : Activity() {

    protected lateinit var presenter: P

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.createView()
    }

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }
}
