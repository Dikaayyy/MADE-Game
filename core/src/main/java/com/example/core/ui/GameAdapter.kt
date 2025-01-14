package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.domain.model.Game

class GameAdapter(private var games: List<Game>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.tvGameTitle.text = game.name
        holder.tvGameRating.text = "Rating: ${game.rating}"
        holder.tvGameReleased.text = "Released: ${game.released}"
        holder.tvGameDescription.text = game.description
        holder.tvGameIsFavorite.text = if (game.isFavorite) "Favorite" else "Not Favorite"
    }

    override fun getItemCount(): Int = games.size

    fun updateGames(newGames: List<Game>) {
        games = newGames
        notifyDataSetChanged()
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGameTitle: TextView = itemView.findViewById(R.id.tvGameTitle)
        val tvGameRating: TextView = itemView.findViewById(R.id.tvGameRating)
        val tvGameReleased: TextView = itemView.findViewById(R.id.tvGameReleased)
        val tvGameDescription: TextView = itemView.findViewById(R.id.tvGameDescription)
        val tvGameIsFavorite: TextView = itemView.findViewById(R.id.tvGameIsFavorite)
    }
}