package com.samiznaetechto.podelam

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TransportSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_transport_select)

        var status = intent.getStringExtra("Status")


        val walk: Button = findViewById(R.id.walkBtn)
        walk.setOnClickListener()
        { i ->
            i.setBackgroundColor(Color.RED)
            val intent = Intent(this, howMuchActivity::class.java)
            intent.putExtra("Status", status)
            intent.putExtra("Transport", "Walk")
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        val bus: Button = findViewById(R.id.busBtn)
        bus.setOnClickListener()
        { i ->
            i.setBackgroundColor(Color.RED)
            val intent = Intent(this, howMuchActivity::class.java)
            intent.putExtra("Status", status)
            intent.putExtra("Transport", "Bus")
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        val car: Button = findViewById(R.id.carBtn)
        car.setOnClickListener()
        { i ->
            i.setBackgroundColor(Color.RED)
            val intent = Intent(this, howMuchActivity::class.java)
            intent.putExtra("Status", status)
            intent.putExtra("Transport", "Car")
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}