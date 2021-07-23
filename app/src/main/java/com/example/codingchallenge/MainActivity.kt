package com.example.codingchallenge

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.codingchallenge.model.Alarm
import com.example.codingchallenge.model.AlarmInstance
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val instanceList: ArrayList<AlarmInstance> = ArrayList()

    private var numberOfAlarmInstance = 0L

    @RequiresApi(Build.VERSION_CODES.O)
    private var endZonedDateTime: ZonedDateTime =
        ZonedDateTime.parse("2021-07-28T00:00+01:00[Asia/Tashkent]")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val gson = GsonBuilder().setPrettyPrinting().create()
        val list: List<*> = Stream.of(
            Alarm(
                1,
                "Yodgorbek",
                1,
                "2020-01-01T00:00+01:00[Asia/Tashkent]",
                "2020-01-11T00:00+01:00[Asia/Tashkent]",
                "2020-01-01T00:00+01:00[Asia/Tashkent]"
            ), Alarm(
                1,
                "TEST",
                1,
                "2020-01-01T00:00+01:00[Europe/Bratislava]",
                "2020-01-11T00:00+01:00[Europe/Bratislava]",
                "2020-01-01T00:00+01:00[Europe/Bratislava]"
            )
        )
            .collect(Collectors.toList())
        println("Convert list of person objects to Json:")

        var zone: ZonedDateTime = ZonedDateTime.parse("2020-01-01T00:00+01:00[Asia/Tashkent]")
        val jsonData = gson.toJson(list) // converts to json
        Log.e("jsonInput", jsonData.toString())

        if (numberOfAlarmInstance == 0L) {
            val currentZonedDateTime = ZonedDateTime.now()
            Log.e("currentZonedDateTime", currentZonedDateTime.toString())

            val currentDate = Date.from(currentZonedDateTime.toInstant())
            Log.e("currentData", currentDate.toString())

            val endDate = Date.from(endZonedDateTime.toInstant())
            Log.e("endDate", endDate.toString())

            numberOfAlarmInstance =
                zonedDateTimeDifference(currentZonedDateTime, endZonedDateTime, ChronoUnit.DAYS)
            Log.e("numberOfDays", numberOfAlarmInstance.toString())


        }
        var count = 0L
        for (i in 1..numberOfAlarmInstance) {

            val groupListType: Type = object : TypeToken<ArrayList<Alarm>?>() {}.type
            val model: ArrayList<Alarm> = gson.fromJson(jsonData, groupListType)

            model.forEach {
                count++
                val zonedDateTime: ZonedDateTime = ZonedDateTime.parse(it.startDate)
                val updatedZonedDateTime = zonedDateTime.plusDays(count)
                Log.e("time", updatedZonedDateTime.toString())

                val alarmInstance = AlarmInstance(updatedZonedDateTime, it.name)
                instanceList.add(alarmInstance)
            }
        }
        Log.e("alarmInstance", instanceList.toString())


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun zonedDateTimeDifference(d1: ZonedDateTime?, d2: ZonedDateTime?, unit: ChronoUnit): Long {
        return unit.between(d1, d2)
    }
}