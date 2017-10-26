package com.jahu.playground.features.dashboard

class DashboardPresenter(
        private val view: DashboardContract.View
) : DashboardContract.Presenter {

    override fun createView() {
        view.showBottomNavigationBar(listOf(
                BottomNavigationItem.QuizSetupItem(),
                BottomNavigationItem.LeaderboardItem(),
                BottomNavigationItem.SettingsItem()
        ))
        view.showQuizSetup()
    }

    override fun resumeView() {}

    override fun onNavigationItemSelected(itemId: Int): Boolean {
        when (itemId) {
            BottomNavigationItem.QUIZ_SETUP -> view.showQuizSetup()
            BottomNavigationItem.LEADERBOARD -> view.showLeaderboard()
            BottomNavigationItem.SETTINGS -> view.showSettings()
        }
        return true
    }

}
