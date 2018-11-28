package com.hanifkf.finalproject.Presenter

import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.ApiRepository.TheSportDBApi
import com.hanifkf.finalproject.CoroutineContextProvider
import com.hanifkf.finalproject.Response.MatchResponse
import com.hanifkf.finalproject.View.MatchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view:MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider= CoroutineContextProvider()){

    fun lastMatch(params:String){
        view.showLoading()
        async(context.main) {
            val data =bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLast(params)), MatchResponse::class.java)
            }

            view.showMatch(data.await().events)
            view.hideLoading()
        }

    }
    fun nextMatch(params:String){
        view.showLoading()
        async(context.main) {
            val data =bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNext(params)), MatchResponse::class.java)
            }

            view.showMatch(data.await().events)

            view.hideLoading()
        }

    }

    fun searchMatchs(params:String){
        view.showLoading()
        async(context.main) {
            val data =bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.searchMatchs(params)), MatchResponse::class.java)
            }

            view.showMatch(data.await().event)

            view.hideLoading()
        }
    }
}