package com.jahu.playground.features.dashboard

import android.app.Fragment
import android.os.Bundle
import com.jahu.playground.R
import com.jahu.playground.features.quizsetup.QuizSetupFragment
import com.jahu.playground.mvp.BaseActivity

class DashboardActivity : BaseActivity<DashboardContract.Presenter>(), DashboardContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        presenter = DashboardPresenter(this)
    }

    override fun showQuizSetupScreen() {
        replaceFragment(QuizSetupFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.dashboardContainer, fragment)
                .commit()
    }

}
