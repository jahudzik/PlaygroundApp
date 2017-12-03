package com.jahu.playground.features.chooseuser

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jahu.playground.R
import com.jahu.playground.dao.User
import com.jahu.playground.features.dashboard.DashboardActivity
import com.jahu.playground.features.edituser.EditUserActivity
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_choose_user.*
import javax.inject.Inject

class ChooseUserActivity : MvpActivity<ChooseUserContract.Presenter>(), ChooseUserContract.View, UsersAdapter.OnUserChosenListener {

    @Inject override lateinit var presenter: ChooseUserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_user)
        getAppComponent()
                .plus(ChooseUserModule(this))
                .inject(this)
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

    override fun navigateToEditUserScreen(mode: EditUserContract.Mode) {
        startActivity(EditUserActivity.getIntent(this, mode))
    }

    override fun onUserChosen(user: User) {
        presenter.onUserChosen(user)
    }

    override fun navigateToApp() {
        finish()
        startActivity(Intent(this, DashboardActivity::class.java))
    }

}
