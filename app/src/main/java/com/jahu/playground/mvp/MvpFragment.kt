package com.jahu.playground.mvp

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class MvpFragment<P : MvpPresenter> : BaseFragment() {

    protected open lateinit var presenter: P

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }

}
