package com.samiznaetechto.podelam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.samiznaetechto.podelam.presenters.TransportPresenter

class TransportSelectActivity : AppCompatActivity() {
    private lateinit var status :String
    private lateinit var tr : TransportPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_transport_select)
        status = intent.getStringExtra("Status").toString()
        tr = TransportPresenter(this)
        setBtnLogic()
    }

    private fun setBtnLogic() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        val walk: Button = findViewById(R.id.walkBtn)
        walk.setOnClickListener { tr.action(it, status, "Walk") }

        val bus: Button = findViewById(R.id.busBtn)
        bus.setOnClickListener { tr.action(it, status, "Bus") }

        val car: Button = findViewById(R.id.carBtn)
        car.setOnClickListener { tr.action(it, status, "Car") }
    }
}