package com.samiznaetechto.podelam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class settingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val reset : Button = findViewById(R.id.resetBtn)
        reset.setOnClickListener {
            val s = SettingsWrapper(applicationInfo.dataDir)
            s.settingReset()
            Toast.makeText(applicationContext, "Перезапустите приложение", Toast.LENGTH_SHORT).show()
        }
    }
}