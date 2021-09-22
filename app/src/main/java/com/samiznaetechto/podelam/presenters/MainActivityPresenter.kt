package com.samiznaetechto.podelam.presenters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import com.samiznaetechto.podelam.TransportSelectActivity

class MainActivityPresenter(context : Context) {
    private var c = context
    fun action(i : View?, status : String) {
        i!!.setBackgroundColor(Color.RED)
        val intent = Intent(c, TransportSelectActivity::class.java).apply {
            putExtra("Status", status)
        }
        c.startActivity(intent)
    }
}