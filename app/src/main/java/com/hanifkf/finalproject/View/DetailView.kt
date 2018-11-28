package com.hanifkf.finalproject.View

import com.hanifkf.finalproject.Model.Detail
import com.hanifkf.finalproject.Model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data : List<Detail>?, homeTeam : List<Team>?, awayTeam: List<Team>?)
}