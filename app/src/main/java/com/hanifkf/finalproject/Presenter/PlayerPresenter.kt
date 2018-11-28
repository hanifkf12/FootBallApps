package com.hanifkf.finalproject.Presenter

import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.ApiRepository.TheSportDBApi
import com.hanifkf.finalproject.CoroutineContextProvider
import com.hanifkf.finalproject.Response.PlayerResponse
import com.hanifkf.finalproject.View.PlayerView
import kotlinx.coroutines.experimental.async

import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getPlayers(params : String){
        view.showLoading()
        async(context.main) {
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamPlayers(params)),PlayerResponse::class.java)
            }

            view.showPlayer(data.await().player)

            view.hideLoading()
        }
    }
    fun getDetailPlayer(params : String){
        view.showLoading()
        async(context.main) {
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailPlayer(params)),PlayerResponse::class.java)
            }

            view.showPlayer(data.await().players)

            view.hideLoading()
        }
    }


}