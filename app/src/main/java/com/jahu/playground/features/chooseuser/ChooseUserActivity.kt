package com.jahu.playground.features.chooseuser

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jahu.playground.R
import com.jahu.playground.dao.User
import com.jahu.playground.features.adduser.AddUserActivity
import com.jahu.playground.features.dashboard.DashboardActivity
import com.jahu.playground.mvp.BaseActivity
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.repositories.mock.MockedLocalDataRepository
import com.jahu.playground.usecases.GetActualUserUseCase
import com.jahu.playground.usecases.GetUsersUseCase
import com.jahu.playground.usecases.SetActualUserUseCase
import kotlinx.android.synthetic.main.activity_choose_user.*

class ChooseUserActivity : BaseActivity<ChooseUserPresenter>(), ChooseUserContract.View, UsersAdapter.OnUserChosenListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_user)
        val preferencesManager = SharedPreferencesManager(this)
        presenter = ChooseUserPresenter(this,
                GetActualUserUseCase(preferencesManager, MockedLocalDataRepository),
                GetUsersUseCase(MockedLocalDataRepository),
                SetActualUserUseCase(preferencesManager))

        addUserButton.setOnClickListener { presenter.onAddUserButtonClicked() }
    }

    override fun showNoUsersMessage() {
        usersRecyclerView.visibility = View.GONE
    }

    override fun showUsersList(users: List<User>) {
        usersRecyclerView.visibility = View.VISIBLE
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = UsersAdapter(users, this)
    }

    override fun navigateToAddUserScreen() {
        startActivity(Intent(this, AddUserActivity::class.java))
    }

    override fun onUserChosen(user: User) {
        presenter.onUserChosen(user)
    }

    override fun navigateToApp() {
        startActivity(Intent(this, DashboardActivity::class.java))
    }

}
