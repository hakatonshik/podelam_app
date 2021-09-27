package com.samiznaetechto.podelam.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samiznaetechto.podelam.*
import com.samiznaetechto.podelam.databinding.ActivityHowMuchBinding

class howMuchActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHowMuchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityHowMuchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val status = intent.getStringExtra("Status")
        val transport = intent.getStringExtra("Transport")
        var _setting : setting?

        binding.submitBtn.setOnClickListener { i ->
            i.setBackgroundColor(Color.RED)
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
                wakeupTime = binding.wakeupTimeTextBox.text.toString().toInt(),
                preparationTime = binding.preparationTimeTextBox.text.toString().toInt(),
                breakfastTime = binding.breakfastTimeTextBox.text.toString().toInt())

            val _set = SettingsWrapper(applicationInfo.dataDir)
            _set.settingSet(_setting!!)
            val intent = Intent(this, defaultActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }
}