package com.example.codingchallenge.model

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class AlarmInstance(
    @SerializedName("zonedDateTime") val time: ZonedDateTime,
    @SerializedName("name") val name: String
)