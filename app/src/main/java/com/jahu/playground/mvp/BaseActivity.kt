package com.jahu.playground.mvp

import android.app.Activity

abstract class BaseActivity<P : BasePresenter> : Activity() {

    protected lateinit var presenter: P

    override fun onResume() {
        super.onResume()
        presenter.initView()
    }
}