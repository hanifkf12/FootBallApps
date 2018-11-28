package com.hanifkf.finalproject.View


import com.hanifkf.finalproject.Model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeam(data : List<Team>?)
}