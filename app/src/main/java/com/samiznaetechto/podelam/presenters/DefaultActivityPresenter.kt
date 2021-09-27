package com.samiznaetechto.podelam.presenters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samiznaetechto.podelam.*
import com.samiznaetechto.podelam.activity.createTaskActivity
import com.samiznaetechto.podelam.activity.settingsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DefaultActivityPresenter (context : Context) {
    private var c = context
    private lateinit var _setting : setting

    suspend fun checkIfThereAreTasks(tx : TextView, rv : RecyclerView) {
        val tb = TaskBuilder(c)
        val d = tb.GetAllTasks()
        val adapter = ToDoAdapter(d)
        rv.let {
            it.adapter = adapter
            it.layoutManager =
                LinearLayoutManager(c).apply { orientation = LinearLayoutManager.VERTICAL }
            }
        }


    suspend fun setInfo(_userStatus : TextView, _userTransport : TextView, addTaskBtn : ImageButton, setBtn : ImageButton) {
        val _settingWrapper = SettingsWrapper(c.applicationInfo.dataDir)

        GlobalScope.launch(Dispatchers.IO) {
            _setting = _settingWrapper.settingRead()
        }.join()

        _userStatus.text = when(_setting.userStatusId)
        {
            userStatus.STUDENT -> "Бедный студент"
            userStatus.WORKINGADULT -> "Работяга"
            userStatus.NOTSTATED -> "Некто"
            userStatus.SCHOOLAR -> "Школота"
        }
        _userTransport.text = when(_setting.userTransportId)
        {
            userTransport.BUS -> "на басике"
            userTransport.PEDESTRIAN -> "ножками"
            userTransport.NOTSTATED -> "а как"
            userTransport.CAR -> "на ласточке"
        }

        setBtn.setOnClickListener {
            val intent = Intent(c, settingsActivity::class.java)
            c.startActivity(intent)
        }

    }


}