package com.hanifkf.finalproject.Response

import com.google.gson.annotations.SerializedName
import com.hanifkf.finalproject.Model.Player

data class PlayerResponse(@field:SerializedName("player")
                        val player :List<Player>? =null,

                        @field:SerializedName("players")
                        val players :List<Player>? =null)