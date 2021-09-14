package com.samiznaetechto.podelam

import android.media.Ringtone
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.samiznaetechto.podelam.exercises.ExerciseGenerator
import kotlin.system.exitProcess


class alarmActivity : AppCompatActivity() {

    lateinit var ringtone : Ringtone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        makeRingtoneAudible()
        var eg = ExerciseGenerator()
        var ex = eg.createExercise()

        var exerciseTextView: TextView = findViewById(R.id.turnOffAlarmTaskTextView)
        exerciseTextView.text = ex.mathStr

        var submitExerciseBtn: Button = findViewById(R.id.btnAnswerTask)
        submitExerciseBtn.setOnClickListener {
            var answer: EditText = findViewById(R.id.textAnswer)
            if (answer.text.toString() == ex.answer.toString()) {
                stopRingtone()
                exitProcess(0)
            } else {
                Toast.makeText(this, "Ответ неверный", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun makeRingtoneAudible() {
        var notificationUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, notificationUri)
        if (ringtone == null) {
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ringtone = RingtoneManager.getRingtone(this, notificationUri)
        }
        if (ringtone != null) {
            ringtone.play()
        }
    }

    private fun stopRingtone()
    {
        if (ringtone != null && ringtone.isPlaying) {
            ringtone.stop()
        }
    }


    override fun onDestroy() {
        stopRingtone()
        super.onDestroy()
    }
}