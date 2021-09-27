package com.samiznaetechto.podelam.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samiznaetechto.podelam.R
import com.samiznaetechto.podelam.SettingsWrapper
import com.samiznaetechto.podelam.databinding.ActivityMainBinding
import com.samiznaetechto.podelam.presenters.MainActivityPresenter

class MainActivity : AppCompatActivity() {

    private lateinit var presenter : MainActivityPresenter
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainActivityPresenter(this)
        setBtnLogic()
        if(SettingsWrapper(applicationInfo.dataDir).isThereSetting())
        {
            val intent = Intent(this, defaultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setBtnLogic()
    {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        binding.studentButton.setOnClickListener { presenter.action(it, "Student") }
        binding.workerButton.setOnClickListener { presenter.action(it, "Worker") }
        binding.notStatedButton.setOnClickListener { presenter.action(it, "Not stated") }
        binding.schoolarButton.setOnClickListener { presenter.action(it, "Schoolar") }
    }
}