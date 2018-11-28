package com.hanifkf.finalproject.Favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanifkf.finalproject.Adapter.ViewPagerAdapter
import com.hanifkf.finalproject.Matches.LastMatchsFragment
import com.hanifkf.finalproject.Matches.NextMatchsFragment

import com.hanifkf.finalproject.R
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_matches.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesFragment : Fragment() {
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavMatchFragment(),"MATCHES")
        adapter.addFragment(FavTeamFragment(),"TEAMS")
        view_pager_favorites.adapter = adapter
        tab_favorites.setupWithViewPager(view_pager_favorites)
    }

}
