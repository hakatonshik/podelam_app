package com.samiznaetechto.podelam.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.samiznaetechto.podelam.Task

object TaskDatabase {
    private lateinit var cv : ContentValues
    private lateinit var dbHelper : DatabaseHelper
    private const val TAG = "DB"
    private lateinit var db : SQLiteDatabase

    private lateinit var TABLE_NAME : String
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

    fun RemoveTask(id : Int)
    {
        Log.e(TAG, "$TABLE_NAME -> DELETE $id")
        db.execSQL("DELETE FROM $TABLE_NAME WHERE _id = $id")
    }

    @SuppressLint("Range")
    fun getLastId() : Int {
        val cursor: Cursor =
            db.query(dbHelper.TABLE_NAME, null, null, null, null, null, null)
        if(cursor.count <= 0) return 1
        cursor.moveToLast()
        Log.e(TAG, "Отправлен последний айди ${cursor.getInt(cursor.getColumnIndex("_id"))}")
        return cursor.getInt(cursor.getColumnIndex("_id"))
    }

    fun RemoveLastTask()
    {
        db.execSQL("DELETE FROM ${dbHelper.TABLE_NAME} WHERE id = (SELECT MAX(id) FROM ${dbHelper.TABLE_NAME});")
    }

    fun AddTaskToDB(task: Task) : Long
    {
        cv.run {
            put(dbHelper.TASK_NAME, task.taskName)
            put(dbHelper.TASK_PLACE, task.taskPlace)
            put(dbHelper.TASK_TARGET, task.taskTarget)
            put(dbHelper.TASK_TIME, task.taskTime)
        }
        val id = db.insert(dbHelper.TABLE_NAME, null, cv)
        Log.e("AddTaskToDB", "Добавлена запись, айди = ${task.id}")
        return id
    }

    fun GetTasks() : Cursor
    {
        Log.e("GetTasks", "Получены значения бд")
        //как же много налов
        return db.query(dbHelper.TABLE_NAME, null, null, null, null, null, null, null)
    }

    fun Close()
    {
        Log.e(TAG, "БД закрыта")
        dbHelper.close()
    }
}