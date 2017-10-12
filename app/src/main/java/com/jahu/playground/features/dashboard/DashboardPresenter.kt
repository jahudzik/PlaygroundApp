package com.jahu.playground.features.dashboard

class DashboardPresenter(
        private val view: DashboardContract.View
) : DashboardContract.Presenter {

    override fun resumeView() {
        view.showQuizSetupScreen()
    }

}
