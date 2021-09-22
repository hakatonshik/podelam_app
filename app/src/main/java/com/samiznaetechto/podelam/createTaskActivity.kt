package com.samiznaetechto.podelam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.timepicker.TimeFormat

import com.google.android.material.timepicker.MaterialTimePicker
import java.util.*


class createTaskActivity : AppCompatActivity() {
    private lateinit var toast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        toast = Toast.makeText(applicationContext, "Всё ли заполнено?", Toast.LENGTH_LONG)
        setContentView(R.layout.activity_create_task)
        setButtonLogic()
    }


    private fun setButtonLogic() {


        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }


        val subbtn: Button = findViewById(R.id.submitTaskButton)
        subbtn.setOnClickListener {


            val eventNameText: EditText = findViewById(R.id.eventNameTextBox)
            if (eventNameText.text == null) {
                toast.show()
                return@setOnClickListener
            }

            val eventNameTime = ""

            val eventPlace: EditText = findViewById(R.id.startPlaceTextBox)
            if (eventPlace.text == null) {
                toast.show()
                return@setOnClickListener
            }

            val eventAim: EditText = findViewById(R.id.targetPlaceNameTextBox)
            if (eventAim.text == null) {
                toast.show()
                return@setOnClickListener
            }

            val intent = Intent().apply {
                putExtra(
                    "taskName",
                    eventNameText.text.toString()
                )

                putExtra(
                    "taskTime",
                    eventNameTime
                )

                putExtra(
                    "taskPlace",
                    eventPlace.text.toString()
                )

                putExtra(
                    "taskTarget",
                    eventAim.text.toString()
                )
            }
            AlarmHandler.create(
                this,
                calendar
            )
            setResult(RESULT_OK, intent)
            finish()
        }


        val cancelBtn: Button = findViewById(R.id.cancelTaskButton)
        cancelBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }


        val timeSelect: Button = findViewById(R.id.timeSelectBtn)
        timeSelect.setOnClickListener {
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время для задачки")
                .build()

            materialTimePicker.addOnPositiveButtonClickListener {
                calendar.apply {
                    set(Calendar.MINUTE, materialTimePicker.minute)
                    set(Calendar.HOUR_OF_DAY, materialTimePicker.hour)
                }
                Toast.makeText(this, "Время установлено", Toast.LENGTH_SHORT).show()
            }
            materialTimePicker.show(supportFragmentManager, "тег")
        }


    }
}