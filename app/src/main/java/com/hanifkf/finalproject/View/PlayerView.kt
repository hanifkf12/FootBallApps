package com.hanifkf.finalproject.View

import com.hanifkf.finalproject.Model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayer(data : List<Player>?)
}