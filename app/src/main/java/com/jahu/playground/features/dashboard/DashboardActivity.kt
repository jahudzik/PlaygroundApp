package com.jahu.playground.features.dashboard

import android.app.Fragment
import android.os.Bundle
import com.jahu.playground.R
import com.jahu.playground.features.quizsetup.QuizSetupFragment
import com.jahu.playground.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : BaseActivity<DashboardContract.Presenter>(), DashboardContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        presenter = DashboardPresenter(this)
    }

    override fun showBottomNavigationBar(items: List<BottomNavigationItem>) {
        for (item in items) {
            bottomNavigationBar.menu
                    .add(0, item.id, item.id, item.title)
                    .icon = getDrawable(item.icon)
        }
    }

    override fun showQuizSetupScreen() {
        switchFragment(QuizSetupFragment.newInstance(), BottomNavigationItem.QUIZ_SETUP)
    }

    private fun switchFragment(fragment: Fragment, itemId: Int) {
        fragmentManager.beginTransaction()
                .replace(R.id.dashboardContainer, fragment)
                .commit()
        bottomNavigationBar.selectedItemId = itemId
    }

}
