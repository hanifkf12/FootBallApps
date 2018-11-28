package com.hanifkf.finalproject.ApiRepository

import com.hanifkf.finalproject.BuildConfig

object TheSportDBApi {

    fun getNext(params: String) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/eventsnextleague.php?id="+params
    }

    fun getLast(params: String):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/eventspastleague.php?id="+params
    }

    fun getDetailEvent(idEvent:String) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookupevent.php?id="+idEvent
    }

    fun getDetailTeam(id :String) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookupteam.php?id="+id
    }

    fun getTeamsLeague(params:String):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookup_all_teams.php?id="+params
    }

    fun searchMatchs(params:String):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/searchevents.php?e="+params
    }

    fun getTeamPlayers(params: String) :String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookup_all_players.php?id="+params
    }

    fun getDetailPlayer(params: String):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookupplayer.php?id="+params
    }
    fun searchTeams(params:String) :String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/searchteams.php?t="+params
    }

}