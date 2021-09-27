package com.samiznaetechto.podelam.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samiznaetechto.podelam.R
import com.samiznaetechto.podelam.databinding.ActivityTransportSelectBinding
import com.samiznaetechto.podelam.presenters.TransportPresenter

class TransportSelectActivity : AppCompatActivity() {
    private lateinit var status :String
    private lateinit var tr : TransportPresenter
    private lateinit var binding : ActivityTransportSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityTransportSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        status = intent.getStringExtra("Status").toString()
        tr = TransportPresenter(this)
        setBtnLogic()
    }

    private fun setBtnLogic() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        binding.walkBtn.setOnClickListener { tr.action(it, status, "Walk") }
        binding.busBtn.setOnClickListener { tr.action(it, status, "Bus") }
        binding.carBtn.setOnClickListener { tr.action(it, status, "Car") }
    }
}