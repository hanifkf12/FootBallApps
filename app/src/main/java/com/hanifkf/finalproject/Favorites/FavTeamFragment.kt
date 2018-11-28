package com.hanifkf.finalproject.Favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanifkf.finalproject.Adapter.FavoriteTeamAdapter
import com.hanifkf.finalproject.Database.database
import com.hanifkf.finalproject.Detail.DetailTeamActivity
import com.hanifkf.finalproject.Model.FavoriteTeam

import com.hanifkf.finalproject.R
import kotlinx.android.synthetic.main.fragment_fav_team.*
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavTeamFragment : Fragment() {
    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycle_fav_team.layoutManager = LinearLayoutManager(ctx)
        adapter = FavoriteTeamAdapter(ctx, favorites) {
            startActivity<DetailTeamActivity>("teamId" to it.teamId, "teamName" to it.teamName)
        }
        recycle_fav_team.adapter = adapter
        showFavorite()
        swipeRefreshfavteam.setOnRefreshListener {
            favorites.clear()
            showFavorite()
        }
    }

    fun showFavorite(){
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            swipeRefreshfavteam.isRefreshing = false
        }
    }
}
