package com.hanifkf.finalproject

import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.ApiRepository.TheSportDBApi
import com.hanifkf.finalproject.Model.Team
import com.hanifkf.finalproject.Presenter.TeamPresenter
import com.hanifkf.finalproject.Response.TeamResponse
import com.hanifkf.finalproject.View.PlayerView
import com.hanifkf.finalproject.View.TeamView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TeamPresenterTest{

    private lateinit var presenter: TeamPresenter

    @Mock
    private
    lateinit var view:TeamView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view,apiRepository,gson, TestContextProvider())
    }

    @Test
    fun testTeams(){
        val team: MutableList<Team> = mutableListOf()
        val response = TeamResponse(team)
        val leagueId = "4328"

        Mockito.`when`(
            gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamsLeague(leagueId)),
                TeamResponse::class.java

            )
        ).thenReturn(response)

        presenter.getTeams(leagueId)
        verify(view).showLoading()
        verify(view).showTeam(team)
        verify(view).hideLoading()
    }
}
