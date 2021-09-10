package com.samiznaetechto.podelam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class settingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        var reset : Button = findViewById<Button>(R.id.resetBtn)
        reset.setOnClickListener() {
            var s = settingsWrapper(applicationInfo.dataDir)
            s.settingReset()
            Toast.makeText(applicationContext, "Перезапустите приложение", Toast.LENGTH_SHORT).show()
        }
    }
}