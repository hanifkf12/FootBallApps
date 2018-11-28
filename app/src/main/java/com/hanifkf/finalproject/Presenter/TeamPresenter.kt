package com.hanifkf.finalproject.Presenter

import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.ApiRepository.TheSportDBApi
import com.hanifkf.finalproject.CoroutineContextProvider
import com.hanifkf.finalproject.Response.TeamResponse
import com.hanifkf.finalproject.View.TeamView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(private val view: TeamView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getTeams(params : String){
        view.showLoading()
        async(context.main) {
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsLeague(params)),TeamResponse::class.java)
            }

            view.showTeam(data.await().teams)

            view.hideLoading()
        }
    }

    fun getDetailTeams(params:String){
        view.showLoading()
        async(context.main) {
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailTeam(params)),TeamResponse::class.java)
            }

            view.showTeam(data.await().teams)

            view.hideLoading()
        }
    }

    fun searchTeams(params: String){
        view.showLoading()
        async(context.main) {
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.searchTeams(params)),TeamResponse::class.java)
            }

            view.showTeam(data.await().teams)

            view.hideLoading()
        }
    }

}