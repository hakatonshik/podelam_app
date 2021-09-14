package com.samiznaetechto.podelam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.timepicker.TimeFormat

import com.google.android.material.timepicker.MaterialTimePicker
import android.view.View
import java.util.*


class createTaskActivity : AppCompatActivity() {
    lateinit var toast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val text = "Всё ли заполнено?"
        val duration = Toast.LENGTH_LONG
        toast = Toast.makeText(applicationContext, text, duration)
        setContentView(R.layout.activity_create_task)
        setButtonLogic()

    }


    private fun setButtonLogic() {


        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)


        var subbtn: Button = findViewById(R.id.submitTaskButton)
        subbtn.setOnClickListener {
            var intent = Intent()

            var eventNameText: EditText = findViewById(R.id.eventNameTextBox)
            if (eventNameText.text == null) {
                toast.show()
                return@setOnClickListener
            }
            intent.putExtra(
                "taskName",
                eventNameText.text.toString()
            )

            var eventNameTime = ""
            intent.putExtra(
                "taskTime",
                eventNameTime
            )

            var eventPlace: EditText = findViewById(R.id.startPlaceTextBox)
            if (eventPlace.text == null) {
                toast.show()
                return@setOnClickListener
            }
            intent.putExtra(
                "taskPlace",
                eventPlace.text.toString()
            )

            var eventAim: EditText = findViewById(R.id.targetPlaceNameTextBox)
            if (eventAim.text == null) {
                toast.show()
                return@setOnClickListener
            }
            intent.putExtra(
                "taskTarget",
                eventAim.text.toString()
            )


            alarmhandler.create(
                this,
                calendar
            )



            setResult(RESULT_OK, intent)
            finish()
        }


        var cancelBtn: Button = findViewById(R.id.cancelTaskButton)
        cancelBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }


        var timeSelect: Button = findViewById(R.id.timeSelectBtn)
        timeSelect.setOnClickListener {
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время для задачки")
                .build()

            materialTimePicker.addOnPositiveButtonClickListener {
                calendar.set(Calendar.MINUTE, materialTimePicker.minute)
                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.hour)
                Toast.makeText(this, "Время установлено", Toast.LENGTH_SHORT).show()
            }
            materialTimePicker.show(supportFragmentManager, "тег")
        }


    }
}