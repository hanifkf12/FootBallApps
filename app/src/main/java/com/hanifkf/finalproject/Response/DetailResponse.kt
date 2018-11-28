package com.hanifkf.finalproject.Response

import com.google.gson.annotations.SerializedName
import com.hanifkf.finalproject.Model.Detail

data class DetailResponse(@field:SerializedName("events")
                          val events :List<Detail>? =null,

                          @field:SerializedName("event")
                          val event :List<Detail>? =null)