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
import com.hanifkf.finalproject.Adapter.ViewPagerAdapter
import com.hanifkf.finalproject.Adapter.ViewPagerTeamAdaterAdapter
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.Database.database
import com.hanifkf.finalproject.Model.FavoriteTeam
import com.hanifkf.finalproject.Model.Team
import com.hanifkf.finalproject.Presenter.TeamPresenter
import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.R.drawable.ic_add_to_favorites
import com.hanifkf.finalproject.R.drawable.ic_added_to_favorites
import com.hanifkf.finalproject.View.TeamView
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(),TeamView {

    private lateinit var presenter:TeamPresenter
    private lateinit var adapter: ViewPagerTeamAdaterAdapter
    private lateinit var teamId:String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)


        intent = intent
        teamId = intent.getStringExtra("teamId")
        val teamName = intent.getStringExtra("teamName")

        supportActionBar?.title = teamName
        adapter = ViewPagerTeamAdaterAdapter(teamId, supportFragmentManager)

        view_pager_teams.adapter = adapter
        tab_detail_teams.setupWithViewPager(view_pager_teams)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this,apiRepository,gson)
        presenter.getDetailTeams(teamId)
        favoriteState()
    }

    override fun showLoading() {
        progressBardetail.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBardetail.visibility = View.GONE
    }

    override fun showTeam(data: List<Team>?) {
        if(data!=null){
            team = Team(data[0].teamId,data[0].teamName,data[0].teamBadge)
            Glide.with(ctx).load(data[0].teamBadge).into(img_team_detail)
            txt_team_detail.text = data[0].teamName
            txt_tahun_detail.text = data[0].teamFormedYear
            txt_stadion_detail.text= data[0].teamStadium
        }
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
                    FavoriteTeam.TABLE_FAVORITE,
                    FavoriteTeam.TEAM_ID to team.teamId,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge)
            }
            toast("Add To Favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try{
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE,"(TEAM_ID = {id})" ,
                    "id" to teamId)
            }
            toast("Remove from Favorite")
        }catch (e :SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
