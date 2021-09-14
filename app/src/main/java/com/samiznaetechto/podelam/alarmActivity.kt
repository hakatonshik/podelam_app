package com.samiznaetechto.podelam

import android.media.Ringtone
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.RingtoneManager
import android.net.Uri


class alarmActivity : AppCompatActivity() {

    lateinit var ringtone : Ringtone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        var notificationUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, notificationUri)
        if (ringtone == null) {
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtone = RingtoneManager.getRingtone(this, notificationUri)
        }
        if (ringtone != null) {
            ringtone.play()
        }
    }

    override fun onDestroy() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop()
        }
        super.onDestroy()
    }
}