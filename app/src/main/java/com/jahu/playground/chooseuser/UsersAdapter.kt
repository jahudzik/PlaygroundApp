package com.jahu.playground.chooseuser

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jahu.playground.R
import com.jahu.playground.dao.User
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(
        private val users: List<User>,
        private val listener: OnUserChosenListener
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(users[position], listener)

    override fun getItemCount() = users.size

    class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(user: User, listener: OnUserChosenListener) {
            with(itemView) {
                userNameTextView.text = user.nick
                setOnClickListener { listener.onUserChosen(user) }
            }
        }

    }

     interface OnUserChosenListener {

        fun onUserChosen(user: User)
    }
}
