package com.lino.shopcart.utils

import com.google.gson.Gson
import com.lino.shopcart.models.MessageResponse

class Utils {
    fun covertResponseMessage(response: String): MessageResponse {
        return Gson().fromJson<MessageResponse>(
            response,
            MessageResponse::class.java
        )

    }
}