package com.jahu.playground.mvp

import javax.inject.Inject

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class MvpFragment<P : MvpPresenter> : BaseFragment() {

    @Inject protected lateinit var presenter: P

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }

}
