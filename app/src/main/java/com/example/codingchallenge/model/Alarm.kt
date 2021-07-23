package com.example.codingchallenge.model

import com.google.gson.annotations.SerializedName

data class Alarm(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("repetition") val repetition: Int,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDateTime") val endDateTime: String?,
    @SerializedName("triggerTime") val triggerTime: String
)