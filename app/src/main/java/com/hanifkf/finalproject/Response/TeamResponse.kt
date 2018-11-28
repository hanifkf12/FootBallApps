package com.hanifkf.finalproject.Response

import com.google.gson.annotations.SerializedName
import com.hanifkf.finalproject.Model.Team

data class TeamResponse(@field:SerializedName("teams")
                        val teams :List<Team>? =null,

                        @field:SerializedName("team")
                        val team :List<Team>? =null)