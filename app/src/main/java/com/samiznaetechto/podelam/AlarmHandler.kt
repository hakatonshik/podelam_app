package com.samiznaetechto.podelam

import android.content.Intent
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import java.util.*




object AlarmHandler {

    fun create(context: Context, calendar : Calendar)
    {
        val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
        val alarmClockInfo = AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), getAlarmInfoPendingIntent(context))
        if (alarm != null) {
            alarm.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent(context))
        }
    }

    fun getAlarmInfoPendingIntent(context : Context): PendingIntent {
        var alarmInfoIntent = Intent(context, defaultActivity::class.java)
        if (alarmInfoIntent != null) {
            alarmInfoIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        return PendingIntent.getActivity(
            context,
            0,
            alarmInfoIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    fun getAlarmActionPendingIntent(context : Context): PendingIntent {
        var intent = Intent(context, alarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}