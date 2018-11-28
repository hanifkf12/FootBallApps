package com.hanifkf.finalproject.Model

import com.google.gson.annotations.SerializedName

data class Player(@SerializedName("idPlayer")
                  var idPlayer:String?=null,
                  @SerializedName("strPlayer")
                  var strPlayer:String?=null,
                  @SerializedName("strPosition")
                  var strPosition:String?=null,
                  @SerializedName("strCutout")
                  var strCutout:String?=null,
                  @SerializedName("strHeight")
                  var strHeight:String?=null,
                  @SerializedName("strWeight")
                  var strWeight:String?=null,
                  @SerializedName("strFanart1")
                  var strFanart1:String?=null,
                  @SerializedName("strDescriptionEN")
                  var strDescriptionEN:String?=null
                    )