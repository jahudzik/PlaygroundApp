package com.jahu.playground.mvp

import android.app.Fragment

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class MvpFragment<P : BasePresenter> : Fragment() {

    protected lateinit var presenter: P

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }

}
