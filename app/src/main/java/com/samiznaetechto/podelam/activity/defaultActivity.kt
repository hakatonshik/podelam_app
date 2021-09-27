package com.samiznaetechto.podelam.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samiznaetechto.podelam.*

import com.samiznaetechto.podelam.data.TaskDatabase
import com.samiznaetechto.podelam.databinding.ActivityDefaultBinding
import com.samiznaetechto.podelam.databinding.ActivityHowMuchBinding
import com.samiznaetechto.podelam.presenters.DefaultActivityPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class defaultActivity : AppCompatActivity() {

    private lateinit var presenter : DefaultActivityPresenter
    private lateinit var binding : ActivityDefaultBinding

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
        val tb = TaskBuilder(this)
        val d = tb.GetAllTasks()
        val _adapter = ToDoAdapter(d)
        presenter = DefaultActivityPresenter(this)

        binding = ActivityDefaultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tasksLayout = findViewById<RecyclerView>(R.id.tasksLayout)
        tasksLayout.adapter = _adapter
        tasksLayout.layoutManager =
            LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }

        GlobalScope.launch(Dispatchers.IO) {
            presenter.checkIfThereAreTasks(binding.currentTasksText, binding.tasksLayout)
        }
        setInfo()

    }


    private fun setInfo() {
        runBlocking { presenter.setInfo(binding.userStatus, binding.userTransport, binding.addTaskBtn, binding.SettingShowBtn) }
        binding.addTaskBtn.setOnClickListener {
            val intent = Intent(this, createTaskActivity::class.java)
            startActivityForResult(intent, 1)
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
        GlobalScope.launch(Dispatchers.IO) {
            presenter.checkIfThereAreTasks(binding.currentTasksText, binding.tasksLayout)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun createEvent(taskName : String, taskTime : String, taskPlace : String, taskTarget : String)
    {
        val d = TaskBuilder(this)
        d.AddTask(Task(TaskDatabase.getLastId(), taskName, taskTime, taskPlace, taskTarget))
        GlobalScope.launch(Dispatchers.IO) {
            presenter.checkIfThereAreTasks(binding.currentTasksText, binding.tasksLayout)
        }
    }

}