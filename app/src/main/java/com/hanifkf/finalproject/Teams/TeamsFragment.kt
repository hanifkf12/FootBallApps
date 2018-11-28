package com.hanifkf.finalproject.Teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.hanifkf.finalproject.Adapter.TeamAdapter
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.Detail.DetailTeamActivity
import com.hanifkf.finalproject.Model.Team
import com.hanifkf.finalproject.Presenter.TeamPresenter

import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.View.TeamView
import kotlinx.android.synthetic.main.fragment_last_matchs.*
import kotlinx.android.synthetic.main.fragment_teams.*
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
class TeamsFragment : Fragment(), TeamView {
    private var team: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var leagueName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val spinnerItems = resources.getStringArray(R.array.league_array)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        setHasOptionsMenu(true)
        val request = ApiRepository()
        val gson = Gson()
        adapter = TeamAdapter(ctx,team){
            startActivity<DetailTeamActivity>("teamId" to it.teamId, "teamName" to it.teamName)
        }

        recycle_teams.layoutManager = LinearLayoutManager(ctx)
        recycle_teams.adapter = adapter

        presenter = TeamPresenter(this,request,gson)
        spinner_teams.adapter= spinnerAdapter
        spinner_teams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_teams.selectedItem.toString()
                when(leagueName){
                    "English Premier League" -> presenter.getTeams("4328")
                    "German Bundesliga" -> presenter.getTeams("4331")
                    "Italian Serie A" -> presenter.getTeams("4332")
                    "French Ligue 1" -> presenter.getTeams("4334")
                    "Spanish La Liga" -> presenter.getTeams("4335")
                    "Netherlands Eredivisie" -> presenter.getTeams("4337")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    override fun showLoading() {
        progressBar3.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar3.visibility = View.GONE
    }

    override fun showTeam(data: List<Team>?) {
        if(data!=null){
            team.clear()
            for(i in data.indices){
                if(data[i].strSport.equals("Soccer")){
                    team.add(data[i])
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.seach_menu,menu)
        val searchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView?
        searchView?.queryHint = "Search teams"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.length >1){
                    presenter.searchTeams(newText)
                }else if (newText.length==0){
                    presenter.getTeams("4328")
                }

                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                presenter.getTeams(leagueName)
                return true
            }
        })
    }

}
