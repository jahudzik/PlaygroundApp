package com.jahu.playground.mvp

@SuppressWarnings("UnnecessaryAbstractClass")
abstract class BasePresenter<T : MvpView> : MvpPresenter {

    override fun createView() {
        // to be implemented by subclasses
    }

    override fun resumeView() {
        // to be implemented by subclasses
    }

}
