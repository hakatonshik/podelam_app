package com.samiznaetechto.podelam

import java.io.File
import java.lang.Exception

enum class userStatus
{
    STUDENT,
    WORKINGADULT,
    NOTSTATED,
    SCHOOLAR
}

enum class userTransport
{
    BUS,
    CAR,
    NOTSTATED,
    PEDESTRIAN
}

data class setting (
    var userStatusId : userStatus,
    var userTransportId : userTransport,
    var wakeupTime : Int,
    var preparationTime : Int,
    var breakfastTime : Int
        )

class SettingsWrapper (appDir : String) {
    private var path = "$appDir/pd_settings.ini"

    fun isThereSetting() = File(path).exists()

    fun settingReset()
    {
        if(isThereSetting())
            File(path).delete()
    }

    suspend fun settingRead() : setting
    {
        val content = File(path).readLines()

        var userStatusId = userStatus.STUDENT //default
        var userTransportId = userTransport.BUS // default
        var wakeupTime = 15 // default
        var preparationTime = 30 // default
        var breakfastTime = 30 // default

        var i = 0
        content.forEach()
        {
            when(i)
            {
                0 -> { //userStatusId
                    when(it)
                    {
                        "1" -> userStatusId = userStatus.STUDENT
                        "2" -> userStatusId = userStatus.WORKINGADULT
                        "3" -> userStatusId = userStatus.NOTSTATED
                        "4" -> userStatusId = userStatus.SCHOOLAR
                    }
                }
                1 -> { //userTransportId
                    when(it)
                    {
                        "1" -> userTransportId = userTransport.BUS
                        "2" -> userTransportId = userTransport.CAR
                        "3" -> userTransportId = userTransport.NOTSTATED
                        "4" -> userTransportId = userTransport.PEDESTRIAN
                    }
                }
                2 -> { //wakeupTime
                    wakeupTime = it.toInt()
                }
                3 -> { //preparationTime
                    preparationTime = it.toInt()
                }
                4 -> { //breakfastTime
                    breakfastTime = it.toInt()
                }
            }
            i++
        }
        return setting(userStatusId, userTransportId, wakeupTime, preparationTime, breakfastTime)
    }

    fun settingSet(_setting: setting)
    {
        settingReset()
        val file = File(path)
        var content = ""
        content += when(_setting.userStatusId) {
            userStatus.STUDENT -> "1\n"
            userStatus.WORKINGADULT -> "2\n"
            userStatus.NOTSTATED -> "3\n"
            userStatus.SCHOOLAR -> "4\n"
        }
        content += when(_setting.userTransportId) {
            userTransport.BUS -> "1\n"
            userTransport.CAR -> "2\n"
            userTransport.NOTSTATED -> "3\n"
            userTransport.PEDESTRIAN -> "4\n"
        }
        content += _setting.wakeupTime.toString() + "\n"
        content += _setting.preparationTime.toString() + "\n"
        content += _setting.breakfastTime.toString() + "\n"
        file.writeText(content)
    }
}