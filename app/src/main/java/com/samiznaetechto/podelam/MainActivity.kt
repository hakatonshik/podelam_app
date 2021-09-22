package com.samiznaetechto.podelam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.samiznaetechto.podelam.presenters.MainActivityPresenter

class MainActivity : AppCompatActivity() {

    private lateinit var presenter : MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
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
        val student: Button = findViewById(R.id.studentButton)
        student.setOnClickListener { presenter.action(it, "Student") }
        val worker: Button = findViewById(R.id.workerButton)
        worker.setOnClickListener { presenter.action(it, "Worker") }
        val notStated: Button = findViewById(R.id.notStatedButton)
        notStated.setOnClickListener { presenter.action(it, "Not stated") }
        val schoolar: Button = findViewById(R.id.schoolarButton)
        schoolar.setOnClickListener { presenter.action(it, "Schoolar") }
    }
}