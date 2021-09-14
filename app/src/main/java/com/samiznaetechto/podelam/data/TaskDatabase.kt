package com.samiznaetechto.podelam.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.samiznaetechto.podelam.Task

object TaskDatabase {
    lateinit var cv : ContentValues
    lateinit var dbHelper : DatabaseHelper
    val TAG = "DB"
    lateinit var db : SQLiteDatabase

    lateinit var TABLE_NAME : String
    lateinit var TASK_NAME : String
    lateinit var TASK_TIME : String
    lateinit var TASK_PLACE : String
    lateinit var TASK_TARGET : String

    fun Create(context : Context)
    {
        cv = ContentValues()
        Log.e(TAG, "База данных была загружена")
        dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
        TABLE_NAME = dbHelper.TABLE_NAME
        TASK_NAME = dbHelper.TASK_NAME
        TASK_TIME = dbHelper.TASK_TIME
        TASK_PLACE = dbHelper.TASK_PLACE
        TASK_TARGET = dbHelper.TASK_TARGET
    }

    fun Reset()
    {
        Log.e(TAG, "База данных была сброшена")
        db.delete(dbHelper.TABLE_NAME, null, null)
    }

    fun RemoveTask(id : Long)
    {

    }

    fun RemoveLastTask()
    {
        db.execSQL("DELETE FROM ${dbHelper.TABLE_NAME} WHERE id = (SELECT MAX(id) FROM ${dbHelper.TABLE_NAME});")
    }

    fun AddTaskToDB(task: Task) : Long
    {
        cv.put(dbHelper.TASK_NAME, task.taskName)
        cv.put(dbHelper.TASK_PLACE, task.taskPlace)
        cv.put(dbHelper.TASK_TARGET, task.taskTarget)
        cv.put(dbHelper.TASK_TIME, task.taskTime)
        var id = db.insert(dbHelper.TABLE_NAME, null, cv)
        Log.e(TAG, "Добавлена запись, айди = $id")
        return id
    }

    fun GetTasks() : Cursor
    {
        Log.e(TAG, "Получены значения бд")
        //как же много налов
        return db.query(dbHelper.TABLE_NAME, null, null, null, null, null, null, null)
    }

    fun Close()
    {
        Log.e(TAG, "БД закрыта")
        dbHelper.close()
    }
}