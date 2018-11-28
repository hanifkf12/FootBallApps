package com.hanifkf.finalproject

import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.ApiRepository.TheSportDBApi
import com.hanifkf.finalproject.Model.Player
import com.hanifkf.finalproject.Presenter.PlayerPresenter
import com.hanifkf.finalproject.Response.PlayerResponse
import com.hanifkf.finalproject.View.PlayerView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayerPresenterTest{
    private lateinit var presenter: PlayerPresenter

    @Mock
    private
    lateinit var view:PlayerView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view,apiRepository,gson, TestContextProvider())
    }

    @Test
    fun getPlayers(){
        val teamId = "133604"
        val player: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(player)

        Mockito.`when`(
            gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamPlayers(teamId)),
                PlayerResponse::class.java

            )
        ).thenReturn(response)

        presenter.getPlayers(teamId)
        verify(view).showLoading()
        verify(view).showPlayer(player)
        verify(view).hideLoading()
    }
}