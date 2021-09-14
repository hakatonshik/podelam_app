package com.samiznaetechto.podelam.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "dbTask", null, 1) {

    val TABLE_NAME = "TASK_TABLE"
    val TASK_NAME = "TASK_NAME"
    val TASK_TIME = "TASK_TIME"
    val TASK_PLACE = "TASK_PLACE"
    val TASK_TARGET = "TASK_TARGET"


    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE    $TABLE_NAME " +
                "        (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "        $TASK_NAME TEXT, " +
                "        $TASK_TIME TEXT, " +
                "        $TASK_PLACE TEXT, " +
                "        $TASK_TARGET TEXT);")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun getName() : String = "dbTasks"
}