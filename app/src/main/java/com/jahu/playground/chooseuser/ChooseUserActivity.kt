package com.jahu.playground.chooseuser

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jahu.playground.R
import com.jahu.playground.dao.User
import com.jahu.playground.repository.mock.MockedLocalDataRepository
import kotlinx.android.synthetic.main.activity_choose_user.*

class ChooseUserActivity : Activity(), ChooseUserContract.View {

    private lateinit var presenter: ChooseUserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_user)
        presenter = ChooseUserPresenter(this, GetUsersUseCase(MockedLocalDataRepository()))
        presenter.initView()

        addUserButton.setOnClickListener { presenter.onAddUserButtonClicked() }
    }

    override fun showNoUsersMessage() {
        usersRecyclerView.visibility = View.GONE
    }

    override fun showUsersList(users: List<User>) {
        usersRecyclerView.visibility = View.VISIBLE
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = UsersAdapter(users)
    }

    override fun navigateToAddUserScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToApp() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
