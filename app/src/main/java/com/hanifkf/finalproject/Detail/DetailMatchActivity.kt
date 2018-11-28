package com.hanifkf.finalproject.Detail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.Database.database
import com.hanifkf.finalproject.Model.*
import com.hanifkf.finalproject.Presenter.DetailPresenter
import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.View.DetailView
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity(), DetailView {


    private lateinit var presenter: DetailPresenter
    private lateinit var eventId:String
    private lateinit var match:FavMatch
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailPresenter(this,request,gson)


        intent = intent
        eventId = intent.getStringExtra("eventId")
        val homeTeamId = intent.getStringExtra("homeTeamId")
        val awayTeamId = intent.getStringExtra("awayTeamId")
        val eventDate = intent.getStringExtra("eventDate")
        val homeScore = intent.getStringExtra("homeScore")
        val homeTeam = intent.getStringExtra("homeTeam")
        val awayScore = intent.getStringExtra("awayScore")
        val awayTeam = intent.getStringExtra("awayTeam")
        val eventTime = intent.getStringExtra("eventTime")
        match = FavMatch(eventId,eventDate,eventTime,homeTeam,homeScore,awayTeam,awayScore,homeTeamId,awayTeamId)
        presenter.getDetail(eventId,homeTeamId,awayTeamId)
        favoriteState()
    }

    override fun showLoading() {
        progressBar32.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar32.visibility = View.GONE
    }

    override fun showMatch(data: List<Detail>?, homeTeam: List<Team>?, awayTeam: List<Team>?) {
        if(data!=null){
            date_match_detail.text = data[0].dateEvent?.formatDateToMatch()
            time_match_detail.text=data[0].eventTime?.formatTimeToMatch()
            home_score_detail.text = data[0].intHomeScore
            away_score_detail.text =data[0].intAwayScore
            scorer_home.text =data[0].strHomeGoalDetails
            scorer_away.text =data[0].strAwayGoalDetails
            shots_home.text =data[0].intHomeShots
            shots_away.text =data[0].intAwayShots
            gk_home.text =data[0].strHomeLineupGoalkeeper
            gk_away.text =data[0].strAwayLineupGoalkeeper
            def_home.text =data[0].strHomeLineupDefense
            def_away.text =data[0].strAwayLineupDefense
            mid_home.text =data[0].strHomeLineupMidfield
            mid_away.text =data[0].strAwayLineupMidfield
            fw_home.text =data[0].strHomeLineupForward
            fw_away.text =data[0].strAwayLineupForward
            subs_home.text =data[0].strHomeLineupSubstitutes
            subs_away.text = data[0].strAwayLineupSubstitutes
        }
        if(homeTeam!=null){
            home_team_detail.text = homeTeam[0].teamName
            Glide.with(ctx).load(homeTeam[0].teamBadge).into(imgHome)
        }
        if(awayTeam!=null){
            away_team_detail.text = awayTeam[0].teamName
            Glide.with(ctx).load(awayTeam[0].teamBadge).into(imgAway)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to match.eventId,
                    Favorite.EVENT_DATE to match.eventDate,
                    Favorite.EVENT_TIME to match.eventTime,
                    Favorite.HOME_TEAM to match.homeTeam,
                    Favorite.SCORE_HOME to match.homeScore,
                    Favorite.AWAY_TEAM to match.awayTeam,
                    Favorite.AWAY_SCORE to match.awayScore,
                    Favorite.ID_HOME to match.homeTeamId,
                    Favorite.ID_AWAY to match.awayTeamId)
            }
            toast("Add To Favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to eventId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to eventId)
            }
            toast("Removed from Favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }
}
