package com.samiznaetechto.podelam

import java.text.DateFormat

class Alarm(val time: DateFormat) {
    fun create()
    {

    }
    override fun toString() : String  = DateFormat.getDateInstance().format(time)

}