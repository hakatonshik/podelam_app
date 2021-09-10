package com.samiznaetechto.podelam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class defaultActivity : AppCompatActivity() {

    override fun onBackPressed() {
        //ничего
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)

        var _settingWrapper = settingsWrapper(applicationInfo.dataDir)
        var _setting = _settingWrapper.settingRead()

        var _userStatus: TextView = findViewById(R.id.userStatus)
        _userStatus.text = when(_setting.userStatusId)
        {
            userStatus.STUDENT -> "Бедный студент"
            userStatus.WORKINGADULT -> "Работяга"
            userStatus.NOTSTATED -> "Некто"
            userStatus.SCHOOLAR -> "Школота"
        }
        var _userTransport: TextView = findViewById(R.id.userTransport)
        _userTransport.text = when(_setting.userTransportId)
        {
            userTransport.BUS -> "на басике"
            userTransport.PEDESTRIAN -> "ножками"
            userTransport.NOTSTATED -> "а как"
            userTransport.CAR -> "на ласточке"
        }

        var setBtn : ImageButton = findViewById(R.id.SettingShowBtn)
        setBtn.setOnClickListener {
            var intent = Intent(this, settingsActivity::class.java)
            startActivity(intent)
        }

    }
}