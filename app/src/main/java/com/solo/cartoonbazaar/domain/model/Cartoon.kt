package com.solo.cartoonbazaar.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
data class Cartoon(
    @PrimaryKey
    val url : String,
    val image : String,
    val duration : Int,

)
