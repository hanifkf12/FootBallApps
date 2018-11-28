package com.hanifkf.finalproject.View

import com.hanifkf.finalproject.Model.Match

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatch(data : List<Match>?)
}