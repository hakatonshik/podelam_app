package com.samiznaetechto.podelam

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class howMuchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_how_much)

        val status = intent.getStringExtra("Status")
        val transport = intent.getStringExtra("Transport")
        var _setting : setting?

        val submit : Button = findViewById(R.id.submitBtn)
        submit.setOnClickListener { i ->
            i.setBackgroundColor(Color.RED)

            val wakeup : TextView = findViewById(R.id.wakeupTimeTextBox)
            val prepare : TextView = findViewById(R.id.preparationTimeTextBox)
            val breakfast : TextView = findViewById(R.id.breakfastTimeTextBox)

            _setting = setting( userStatusId = when(status)
                    {
                        "Student" -> userStatus.STUDENT
                        "Worker" -> userStatus.WORKINGADULT
                        "Not stated" -> userStatus.NOTSTATED
                        "Schoolar" -> userStatus.SCHOOLAR
                        else -> userStatus.NOTSTATED
                    },
                userTransportId = when(transport)
                {
                    "Bus" -> userTransport.BUS
                    "Car" -> userTransport.CAR
                    "Not stated" -> userTransport.NOTSTATED
                    "Walk" -> userTransport.PEDESTRIAN
                    else -> userTransport.NOTSTATED
                },
                wakeupTime = wakeup.text.toString().toInt(),
                preparationTime = prepare.text.toString().toInt(),
                breakfastTime = breakfast.text.toString().toInt())
            val _set = SettingsWrapper(applicationInfo.dataDir)
            _set.settingSet(_setting!!)
            val intent = Intent(this, defaultActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }
}