package com.hanifkf.finalproject.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanifkf.finalproject.Model.Favorite
import com.hanifkf.finalproject.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.view.*

class FavoriteAdapter(private val context: Context, private val items: List<Favorite>, private val listener: (Favorite)->Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_match,parent,false)
        val viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }



    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(items: Favorite, listener: (Favorite) -> Unit){
            itemView.home_team.text = items.homeTeam
            itemView.away_team.text= items.awayTeam
            itemView.home_score.text = items.scoreHome
            itemView.away_score.text = items.awayScore
            itemView.date_match.text = items.date
            itemView.time_match.text = items.time
            containerView.setOnClickListener {
                listener(items)
            }
        }

    }

}