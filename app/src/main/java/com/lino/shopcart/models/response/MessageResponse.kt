package com.lino.shopcart.models.response

import com.google.gson.annotations.SerializedName

data class MessageResponse (
    @SerializedName("status_code") var code:Int,
    @SerializedName("status_message") var message: String
)
