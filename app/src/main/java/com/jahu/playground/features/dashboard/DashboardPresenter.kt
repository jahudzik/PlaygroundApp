package com.jahu.playground.features.dashboard

class DashboardPresenter(
        private val view: DashboardContract.View
) : DashboardContract.Presenter {

    override fun resumeView() {
        view.showBottomNavigationBar(listOf(
                BottomNavigationItem.QuizSetupItem(),
                BottomNavigationItem.LeaderboardItem(),
                BottomNavigationItem.SettingsItem()
        ))
        view.showQuizSetupScreen()
    }

}
