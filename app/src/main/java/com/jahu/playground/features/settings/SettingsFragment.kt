package com.jahu.playground.features.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jahu.playground.R
import com.jahu.playground.features.chooseuser.ChooseUserActivity
import com.jahu.playground.mvp.MvpFragment
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.usecases.users.SetActualUserUseCase
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : MvpFragment<SettingsPresenter>(), SettingsContract.View {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SettingsPresenter(this, SetActualUserUseCase(SharedPreferencesManager(activity)))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onResume() {
        super.onResume()
        logoutButton.setOnClickListener { presenter.onLogoutButtonClicked() }
    }

    override fun logout() {
        activity.finish()
        startActivity(Intent(activity, ChooseUserActivity::class.java))
    }
}
