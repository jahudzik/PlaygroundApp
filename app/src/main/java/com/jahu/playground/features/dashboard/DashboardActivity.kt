package com.jahu.playground.features.dashboard

import android.os.Bundle
import com.jahu.playground.R
import com.jahu.playground.mvp.BaseActivity
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.repositories.mock.MockedLocalDataRepository
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : BaseActivity<DashboardContract.Presenter>(), DashboardContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val preferencesManager = SharedPreferencesManager(this)
        presenter = DashboardPresenter(this, preferencesManager, MockedLocalDataRepository)
    }

    override fun showUserName(userName: String) {
        welcomeMessageTextView.text = getString(R.string.welcome_message, userName)
    }

}
