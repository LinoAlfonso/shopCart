package com.lino.shopcart.models

import com.google.gson.annotations.SerializedName

data class MessageResponse (
    @SerializedName("status_code") var code:Int,
    @SerializedName("status_message") var message: String
)
