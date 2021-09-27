package com.samiznaetechto.podelam

import android.content.Intent
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import com.samiznaetechto.podelam.activity.alarmActivity
import com.samiznaetechto.podelam.activity.defaultActivity
import java.util.*




object AlarmHandler {

    fun create(context: Context, calendar: Calendar) {
        val alarmClockInfo = AlarmManager.AlarmClockInfo(
            calendar.timeInMillis,
            getAlarmInfoPendingIntent(context)
        )
        (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?)?.setAlarmClock(
            alarmClockInfo,
            getAlarmActionPendingIntent(context)
        )
    }

    private fun getAlarmInfoPendingIntent(context : Context): PendingIntent {
        val alarmInfoIntent = Intent(context, defaultActivity::class.java)
        alarmInfoIntent.apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        return PendingIntent.getActivity(
            context,
            0,
            alarmInfoIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun getAlarmActionPendingIntent(context : Context): PendingIntent {
        val intent = Intent(context, alarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}