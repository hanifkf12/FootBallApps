package com.hanifkf.finalproject.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hanifkf.finalproject.Detail.DetailOverviewFragment
import com.hanifkf.finalproject.Detail.PlayersFragment

class ViewPagerTeamAdaterAdapter(private val teamId:String, fm: FragmentManager?): FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> DetailOverviewFragment.newInstance(teamId)
            1-> PlayersFragment.newInstance(teamId)
            else-> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "OVERVIEW"
            1-> "PLAYERS"
            else-> null
        }
    }
}