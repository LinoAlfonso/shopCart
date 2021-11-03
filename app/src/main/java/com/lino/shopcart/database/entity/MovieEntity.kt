package com.lino.shopcart.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) var idMovie:Long,
    var titleMovie:String,
    var descriptionMovie:String,
    var imageMovie:String
)
