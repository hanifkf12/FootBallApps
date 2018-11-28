package com.hanifkf.finalproject.Response

import com.google.gson.annotations.SerializedName
import com.hanifkf.finalproject.Model.Match

data  class MatchResponse(
    @field:SerializedName("events")
    val events :List<Match>? =null,

    @field:SerializedName("event")
    val event :List<Match>? =null
)