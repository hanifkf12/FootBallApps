package com.hanifkf.finalproject

import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.ApiRepository.TheSportDBApi
import com.hanifkf.finalproject.Model.Match
import com.hanifkf.finalproject.Presenter.MatchPresenter
import com.hanifkf.finalproject.Response.MatchResponse
import com.hanifkf.finalproject.View.MatchView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest{
    private lateinit var presenter: MatchPresenter

    @Mock
    private
    lateinit var view:MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view,apiRepository,gson, TestContextProvider())
    }

    @Test
    fun testGetLastMatch(){
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val leagueId = "4328"

        Mockito.`when`(
            gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLast(leagueId)),
                MatchResponse::class.java

            )
        ).thenReturn(response)

        presenter.lastMatch(leagueId)
        verify(view).showLoading()
        verify(view).showMatch(match)
        verify(view).hideLoading()
    }
    @Test
    fun testGetNextMatch(){
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val leagueId = "4328"

        Mockito.`when`(
            gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getNext(leagueId)),
                MatchResponse::class.java

            )
        ).thenReturn(response)

        presenter.nextMatch(leagueId)
        verify(view).showLoading()
        verify(view).showMatch(match)
        verify(view).hideLoading()
    }

}