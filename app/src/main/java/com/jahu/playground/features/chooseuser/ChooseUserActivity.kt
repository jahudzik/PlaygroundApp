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
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.repositories.mock.MockedLocalDataRepository
import com.jahu.playground.usecases.users.GetActualUserUseCase
import com.jahu.playground.usecases.users.GetUsersUseCase
import com.jahu.playground.usecases.users.SetActualUserUseCase
import kotlinx.android.synthetic.main.activity_choose_user.*

class ChooseUserActivity : MvpActivity<ChooseUserPresenter>(), ChooseUserContract.View, UsersAdapter.OnUserChosenListener {

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
        val intent = Intent(this, EditUserActivity::class.java)
        intent.putExtra(EditUserActivity.MODE_EXTRA_KEY, EditUserContract.Mode.ADD_USER)
        startActivity(intent)
    }

    override fun onUserChosen(user: User) {
        presenter.onUserChosen(user)
    }

    override fun navigateToApp() {
        finish()
        startActivity(Intent(this, DashboardActivity::class.java))
    }

}
