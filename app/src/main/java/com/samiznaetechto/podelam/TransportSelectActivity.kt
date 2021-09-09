package com.samiznaetechto.podelam

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TransportSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_select)
        val walk: Button = findViewById(R.id.walkBtn)
        walk.setOnClickListener()
        { i ->
            i.setBackgroundColor(Color.RED)
            val intent = Intent(this, defaultActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        val bus: Button = findViewById(R.id.busBtn)
        bus.setOnClickListener()
        { i ->
            i.setBackgroundColor(Color.RED)
            val intent = Intent(this, defaultActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        val car: Button = findViewById(R.id.carBtn)
        car.setOnClickListener()
        { i ->
            i.setBackgroundColor(Color.RED)
            val intent = Intent(this, defaultActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}