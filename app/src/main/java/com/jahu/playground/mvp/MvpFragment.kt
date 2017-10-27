package com.jahu.playground.mvp

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class MvpFragment<P : BasePresenter> : BaseFragment() {

    protected lateinit var presenter: P

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }

}
