package com.jahu.playground.features.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.jahu.playground.R
import com.jahu.playground.features.chooseuser.ChooseUserActivity
import com.jahu.playground.features.edituser.EditUserActivity
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : MvpFragment<SettingsContract.Presenter>(), SettingsContract.View {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent()
                .plus(SettingsModule(this))
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onResume() {
        super.onResume()
        editUserButton.setOnClickListener { presenter.onEditUserButtonClicked() }
        licensesButton.setOnClickListener { presenter.onLicensesButtonClicked() }
        logoutButton.setOnClickListener { presenter.onLogoutButtonClicked() }
    }

    override fun navigateToEditUserScreen(mode: EditUserContract.Mode) {
        startActivity(EditUserActivity.getIntent(activity, mode))
    }

    override fun navigateToLicensesScreen() {
        startActivity(Intent(activity, OssLicensesMenuActivity::class.java))
    }

    override fun logout() {
        activity.finish()
        startActivity(Intent(activity, ChooseUserActivity::class.java))
    }
}
