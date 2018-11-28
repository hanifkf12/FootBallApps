package com.hanifkf.finalproject.Matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView

import android.view.*
import com.hanifkf.finalproject.Adapter.MatchAdapter
import com.hanifkf.finalproject.Adapter.ViewPagerAdapter
import com.hanifkf.finalproject.Detail.DetailMatchActivity
import com.hanifkf.finalproject.Model.Match
import com.hanifkf.finalproject.Presenter.MatchPresenter
import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.Search.SearchMatchActivity
import kotlinx.android.synthetic.main.fragment_last_matchs.*
import kotlinx.android.synthetic.main.fragment_matches.*
import kotlinx.android.synthetic.main.fragment_matches.view.*
import kotlinx.android.synthetic.main.fragment_next_matchs.*
import org.jetbrains.anko.appcompat.v7.coroutines.onClose
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchesFragment : Fragment() {
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapterR: MatchAdapter

    private lateinit var adapter: ViewPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_matches, container, false)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
        setHasOptionsMenu(true)
    }

    fun initViewPager(){
        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(LastMatchsFragment(),"LAST")
        adapter.addFragment(NextMatchsFragment(),"NEXT")
        view_pager_matchs.adapter = adapter
        tab_matches.setupWithViewPager(view_pager_matchs)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.seach_menu,menu)
        val searchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView?
        searchView?.queryHint = "Search matches"
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                context?.startActivity<SearchMatchActivity>("params" to query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })



    }



}
