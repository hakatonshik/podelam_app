package com.samiznaetechto.podelam

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        var setting = settingsWrapper(applicationInfo.dataDir)
        if(setting.isThereSetting())
        {
            val intent = Intent(this, defaultActivity::class.java)
            startActivity(intent)
        }

        val student: Button = findViewById(R.id.studentButton)
        student.setOnClickListener()
        { i ->
            i.setBackgroundColor(RED)
            val intent = Intent(this, TransportSelectActivity::class.java)
            intent.putExtra("Status", "Student")
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        val worker: Button = findViewById(R.id.workerButton)
        worker.setOnClickListener()
        { i ->
            i.setBackgroundColor(RED)
            val intent = Intent(this, TransportSelectActivity::class.java)
            intent.putExtra("Status", "Worker")
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        val notStated: Button = findViewById(R.id.notStatedButton)
        notStated.setOnClickListener()
        { i ->
            i.setBackgroundColor(RED)
            val intent = Intent(this, TransportSelectActivity::class.java)
            intent.putExtra("Status", "Not stated")
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        val schoolar: Button = findViewById(R.id.schoolarButton)
        schoolar.setOnClickListener()
        { i ->
            i.setBackgroundColor(RED)
            val intent = Intent(this, TransportSelectActivity::class.java)
            intent.putExtra("Status", "Schoolar")
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

    }


}