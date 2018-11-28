package com.hanifkf.finalproject.Search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.google.gson.Gson
import com.hanifkf.finalproject.Adapter.MatchAdapter
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.Detail.DetailMatchActivity
import com.hanifkf.finalproject.Model.Match
import com.hanifkf.finalproject.Presenter.MatchPresenter
import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.View.MatchView
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class SearchMatchActivity : AppCompatActivity(), MatchView {
    override fun showLoading() {
        progressSearch.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressSearch.visibility = View.GONE
    }

    override fun showMatch(data: List<Match>?) {
        if(data!=null){
            match.clear()
            for(i in data.indices){
                if(data[i].strSport.equals("Soccer")){
                    match.add(data[i])
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    private var match: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        intent =intent
        val params = intent.getStringExtra("params")
        val request = ApiRepository()
        val gson = Gson()
        adapter = MatchAdapter(ctx,match){
            startActivity<DetailMatchActivity>("eventId" to it.eventId, "homeTeamId" to it.homeTeamId, "awayTeamId" to it.awayTeamId, "eventDate" to it.eventDate?.formatDateToMatch(), "homeScore" to it.homeScore,
                "homeTeam" to it.homeTeam, "awayScore" to it.awayScore, "awayTeam" to it.awayTeam, "eventTime" to it.eventTime?.formatTimeToMatch())
        }

        recycle_search.layoutManager = LinearLayoutManager(ctx)
        recycle_search.adapter = adapter
        presenter = MatchPresenter(this,request,gson)
        presenter.searchMatchs(params)
    }
    var tz : TimeZone = TimeZone.getTimeZone("WIB")
    fun String.formatTimeToMatch(inputFormat: String = "HH:mm:ss",
                                 outputFormat: String = "HH:mm"): String {

        val timeFormat = SimpleDateFormat(inputFormat, Locale.ENGLISH)
        timeFormat.timeZone = tz
        val time = timeFormat.parse(this)

        val returnFormat = SimpleDateFormat(outputFormat, Locale.ENGLISH)
        return returnFormat.format(time)
    }
    fun Date.formatDateToMatch(): String? {
        return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.seach_menu, menu)
        val searchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView?
        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length >0){
                    presenter.searchMatchs(newText)
                }

                return false
            }
        })




        return true
    }
}
