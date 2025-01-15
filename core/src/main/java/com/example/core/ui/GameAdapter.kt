package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.domain.model.Game

class GameAdapter(private val onClick: (Game) -> Unit) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val games = mutableListOf<Game>()

    fun updateGames(newGames: List<Game>) {
        games.clear()
        games.addAll(newGames)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
    }

    override fun getItemCount(): Int = games.size

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvGameTitle: TextView = itemView.findViewById(R.id.tvGameTitle)
        private val tvGameRating: TextView = itemView.findViewById(R.id.tvGameRating)
        private val tvGameReleased: TextView = itemView.findViewById(R.id.tvGameReleased)

        fun bind(game: Game) {
            tvGameTitle.text = game.name
            tvGameRating.text = "Rating: ${game.rating}"
            tvGameReleased.text = "Released: ${game.released}"
            itemView.setOnClickListener { onClick(game) }
        }
    }
}