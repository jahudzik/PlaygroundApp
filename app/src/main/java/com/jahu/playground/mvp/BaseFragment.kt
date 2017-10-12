package com.jahu.playground.mvp

import android.app.Fragment

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class BaseFragment<P : BasePresenter> : Fragment() {

    protected lateinit var presenter: P

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }

}
