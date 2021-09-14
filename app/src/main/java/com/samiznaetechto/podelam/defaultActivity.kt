package com.samiznaetechto.podelam

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.R.*
import android.view.View

import android.widget.LinearLayout





class defaultActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        val pid = Process.myPid()
                        Process.killProcess(pid)
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        //ничего
                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Вы действительно хотите выйти?").setPositiveButton("Да", dialogClickListener)
            .setNegativeButton("Нет", dialogClickListener).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_default)


        checkIfThereAreTasks()
        setInfo()

    }

    private fun setInfo() {
        var _settingWrapper = SettingsWrapper(applicationInfo.dataDir)
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

        var addTaskBtn : ImageButton = findViewById(R.id.addTaskBtn)
        addTaskBtn.setOnClickListener {
            var intent = Intent(this, createTaskActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    private fun checkIfThereAreTasks() {
        val tb = TaskBuilder(this)
        var linearLayout = findViewById<LinearLayout>(R.id.tasksLayout)
        var d = tb.GetAllTasks()
        if(d == null) {
            var text : TextView = findViewById(R.id.currentTasksText)
            text.text = "Задачек нет :("
        }
        else {
            linearLayout.removeAllViewsInLayout()
            d.forEach()
            {
                val taskTextView = TextView(this)
                taskTextView.textSize = 15f
                taskTextView.text =
                    "${it.taskName} = ${it.taskPlace} = ${it.taskTarget} =${it.taskTime}"
                linearLayout.addView(taskTextView)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_CANCELED) {
            if (data != null) {
                createEvent(
                    data.getStringExtra("taskName")!!,
                    data.getStringExtra("taskTime")!!,
                    data.getStringExtra("taskPlace")!!,
                    data.getStringExtra("taskTarget")!!,
                )
            }
        }
        checkIfThereAreTasks()
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun createEvent(taskName : String, taskTime : String, taskPlace : String, taskTarget : String)
    {
        var d = TaskBuilder(this)
        d.AddTask(Task(taskName, taskTime, taskPlace, taskTarget))
        val text = "$taskName, $taskTime, $taskPlace, $taskTarget"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        checkIfThereAreTasks()
    }

}