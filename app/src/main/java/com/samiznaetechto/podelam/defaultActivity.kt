package com.samiznaetechto.podelam

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

import com.samiznaetechto.podelam.data.TaskDatabase


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
        val _settingWrapper = SettingsWrapper(applicationInfo.dataDir)
        val _setting = _settingWrapper.settingRead()

        val _userStatus: TextView = findViewById(R.id.userStatus)
        _userStatus.text = when(_setting.userStatusId)
        {
            userStatus.STUDENT -> "Бедный студент"
            userStatus.WORKINGADULT -> "Работяга"
            userStatus.NOTSTATED -> "Некто"
            userStatus.SCHOOLAR -> "Школота"
        }
        val _userTransport: TextView = findViewById(R.id.userTransport)
        _userTransport.text = when(_setting.userTransportId)
        {
            userTransport.BUS -> "на басике"
            userTransport.PEDESTRIAN -> "ножками"
            userTransport.NOTSTATED -> "а как"
            userTransport.CAR -> "на ласточке"
        }

        val setBtn : ImageButton = findViewById(R.id.SettingShowBtn)
        setBtn.setOnClickListener {
            val intent = Intent(this, settingsActivity::class.java)
            startActivity(intent)
        }

        val addTaskBtn : ImageButton = findViewById(R.id.addTaskBtn)
        addTaskBtn.setOnClickListener {
            val intent = Intent(this, createTaskActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    private fun checkIfThereAreTasks() {
        val tb = TaskBuilder(this)
        val linearLayout = findViewById<LinearLayout>(R.id.tasksLayout)
        val d = tb.GetAllTasks()
        if(d == null) {
            val text : TextView = findViewById(R.id.currentTasksText)
            text.text = "Задачек нет :("
            return
        }
        else {
            linearLayout.removeAllViewsInLayout()
            d.forEach()
            {
                val t = it
                Log.e("checkIfThereAreTasks", "Получена задача от GetAllTasks, айди = ${it.id}")
                val taskTextView = TextView(this).apply {
                    textSize = 15f
                    text = "${it.taskName} = ${it.taskPlace} = ${it.taskTarget} =${it.taskTime}"
                }
                linearLayout.addView(taskTextView)
                val removeBtn = Button(this).apply {
                    text = "удалить"
                }
                removeBtn.setOnClickListener {
                    val d = TaskBuilder(this)
                    Log.e("setOnClickListener", "Удалена запись с айди ${t.id}")
                    d.RemoveTask(t.id)
                    linearLayout.removeAllViews()
                    checkIfThereAreTasks()
                }
                linearLayout.addView(removeBtn)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_CANCELED) {
            data?.let {
                createEvent(
                    it.getStringExtra("taskName")!!,
                    it.getStringExtra("taskTime")!!,
                    it.getStringExtra("taskPlace")!!,
                    it.getStringExtra("taskTarget")!!,
                )
            }
        }
        checkIfThereAreTasks()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun createEvent(taskName : String, taskTime : String, taskPlace : String, taskTarget : String)
    {
        val d = TaskBuilder(this)
        d.AddTask(Task(TaskDatabase.getLastId(), taskName, taskTime, taskPlace, taskTarget))
        checkIfThereAreTasks()
    }

}