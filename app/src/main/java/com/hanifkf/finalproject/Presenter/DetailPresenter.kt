package com.hanifkf.finalproject.Presenter

import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.ApiRepository.TheSportDBApi
import com.hanifkf.finalproject.CoroutineContextProvider
import com.hanifkf.finalproject.Response.DetailResponse
import com.hanifkf.finalproject.Response.TeamResponse
import com.hanifkf.finalproject.View.DetailView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository, private  val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getDetail(id:String, idHome:String, idAway:String){
        view.showLoading()
        async (context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailEvent(id)), DetailResponse::class.java)
            }
            val homeTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailTeam(idHome)), TeamResponse::class.java)
            }
            val awayTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailTeam(idAway)), TeamResponse::class.java)
            }

            view.showMatch(data.await().events, homeTeam.await().teams, awayTeam.await().teams)

            view.hideLoading()
        }
    }
}