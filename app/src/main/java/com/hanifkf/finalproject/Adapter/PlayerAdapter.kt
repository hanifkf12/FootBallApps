package com.hanifkf.finalproject.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hanifkf.finalproject.Model.Player
import com.hanifkf.finalproject.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_player.view.*
import kotlinx.android.synthetic.main.item_teams.view.*

class PlayerAdapter(private val context: Context, private val items: List<Player>, private val listener:(Player)->Unit) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_player,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }


    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(items: Player, listener: (Player) -> Unit){
            Glide.with(itemView.context).load(items.strCutout).into(itemView.img_player)
            itemView.txt_player.text = items.strPlayer
            itemView.txt_position.text = items.strPosition
            containerView.setOnClickListener {
                listener(items)
            }
        }
    }


}