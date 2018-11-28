package com.hanifkf.finalproject.Favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanifkf.finalproject.Adapter.FavoriteAdapter
import com.hanifkf.finalproject.Database.database
import com.hanifkf.finalproject.Detail.DetailMatchActivity
import com.hanifkf.finalproject.Model.Favorite

import com.hanifkf.finalproject.R
import kotlinx.android.synthetic.main.fragment_fav_match.*
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
class FavMatchFragment : Fragment() {
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycle_fav.layoutManager = LinearLayoutManager(ctx)
        adapter = FavoriteAdapter(ctx, favorites) {
            startActivity<DetailMatchActivity>("eventId" to it.idEvent, "homeTeamId" to it.idHome, "awayTeamId" to it.idAway,
                "eventDate" to it.date, "homeScore" to it.scoreHome, "homeTeam" to it.homeTeam, "awayScore" to it.awayScore, "awayTeam" to it.awayTeam, "eventTime" to it.time)
        }
        recycle_fav.adapter = adapter
        showFavorite()
        swipeRefresh3.setOnRefreshListener {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite() {
        context?.database?.use {
            favorites.clear()
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            swipeRefresh3.isRefreshing = false
        }
    }
}
