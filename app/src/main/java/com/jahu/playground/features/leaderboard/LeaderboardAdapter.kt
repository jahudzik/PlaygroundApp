package com.jahu.playground.features.leaderboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jahu.playground.R
import com.jahu.playground.data.entities.LeaderboardEntry
import kotlinx.android.synthetic.main.item_leaderboard.view.*

class LeaderboardAdapter(
        private val entries: List<LeaderboardEntry>
) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_leaderboard, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position, entries[position])

    override fun getItemCount() = entries.size

    class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(rank: Int, entry: LeaderboardEntry) {
            with(itemView) {
                rankLabel.text = context.getString(R.string.rank_label, rank + 1)
                nickLabel.text = entry.userNick
                averageScoreLabel.text = entry.averageScore.toString()
                gamesCountLabel.text = context.getString(R.string.games_count_label, entry.gamesPlayed)
            }
        }
    }
}
