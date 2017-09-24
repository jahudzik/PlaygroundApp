package com.jahu.playground.mvp

import android.app.Activity

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class BaseActivity<P : BasePresenter> : Activity() {

    protected lateinit var presenter: P

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }
}
