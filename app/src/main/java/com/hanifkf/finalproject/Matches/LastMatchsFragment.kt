package com.hanifkf.finalproject.Matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import com.google.gson.Gson
import com.hanifkf.finalproject.Adapter.MatchAdapter
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.Detail.DetailMatchActivity
import com.hanifkf.finalproject.Model.Match
import com.hanifkf.finalproject.Presenter.MatchPresenter

import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.View.MatchView
import kotlinx.android.synthetic.main.fragment_last_matchs.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchsFragment : Fragment(), MatchView {


    private var match: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var leagueName: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_matchs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val spinnerItems = resources.getStringArray(R.array.league_array)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        adapter = MatchAdapter(ctx,match){
            startActivity<DetailMatchActivity>("eventId" to it.eventId, "homeTeamId" to it.homeTeamId, "awayTeamId" to it.awayTeamId, "eventDate" to it.eventDate?.formatDateToMatch(), "homeScore" to it.homeScore,
                "homeTeam" to it.homeTeam, "awayScore" to it.awayScore, "awayTeam" to it.awayTeam, "eventTime" to it.eventTime?.formatTimeToMatch())
        }
        recycle_last.layoutManager = LinearLayoutManager(ctx)
        recycle_last.adapter = adapter
        presenter = MatchPresenter(this,request,gson)
        spinner_last.adapter= spinnerAdapter
        spinner_last.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_last.selectedItem.toString()
                when(leagueName){
                    "English Premier League" -> presenter.lastMatch("4328")
                    "German Bundesliga" -> presenter.lastMatch("4331")
                    "Italian Serie A" -> presenter.lastMatch("4332")
                    "French Ligue 1" -> presenter.lastMatch("4334")
                    "Spanish La Liga" -> presenter.lastMatch("4335")
                    "Netherlands Eredivisie" -> presenter.lastMatch("4337")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
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
    override fun showLoading() {
        progressBar1.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar1.visibility = View.GONE
    }

    override fun showMatch(data: List<Match>?) {
        if(data!=null){
            Log.d("showMatch ",data.size.toString())
            match.clear()
            match.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }
}
