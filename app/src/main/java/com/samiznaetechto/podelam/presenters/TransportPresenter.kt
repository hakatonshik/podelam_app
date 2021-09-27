package com.samiznaetechto.podelam.presenters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import com.samiznaetechto.podelam.activity.howMuchActivity

class TransportPresenter(context : Context) {
    private var c = context
    fun action(i : View?, status : String, transport : String) {
        i!!.apply {
            setBackgroundColor(Color.RED)
        }
        val intent = Intent(c, howMuchActivity::class.java).apply {
            putExtra("Status", status)
            putExtra("Transport", transport)
        }
        c.startActivity(intent)
    }
}