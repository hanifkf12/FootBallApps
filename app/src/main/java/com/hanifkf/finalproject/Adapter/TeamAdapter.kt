package com.hanifkf.finalproject.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hanifkf.finalproject.Model.Team
import com.hanifkf.finalproject.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_teams.view.*

class TeamAdapter(private val context: Context, private val items: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_teams, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(items: Team, listener: (Team) -> Unit) {

            Glide.with(itemView.context).load(items.teamBadge).into(itemView.img_team)
            itemView.txt_team.text = items.teamName
            containerView.setOnClickListener {
                listener(items)
            }


        }
    }

}