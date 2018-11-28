package com.hanifkf.finalproject.Model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Match(@SerializedName("idEvent")
                 val eventId: String? = null,

                 @SerializedName("dateEvent")
                 val eventDate: Date? = null,

                 @SerializedName("strTime")
                 val eventTime: String? = null,

                 @SerializedName("strHomeTeam")
                 val homeTeam: String? = null,

                 @SerializedName("intHomeScore")
                 val homeScore: String? = null,

                 @SerializedName("strAwayTeam")
                 val awayTeam: String? = null,

                 @SerializedName("intAwayScore")
                 val awayScore: String? = null,

                 @SerializedName("idHomeTeam")
                 val homeTeamId: String? = null,

                 @SerializedName("idAwayTeam")
                 val awayTeamId: String? = null,
                 @SerializedName("strSport")
                 val strSport: String? = null
)