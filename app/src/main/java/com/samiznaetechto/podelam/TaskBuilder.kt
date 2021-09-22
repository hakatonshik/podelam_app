package com.samiznaetechto.podelam

import android.content.Context
import android.util.Log
import com.samiznaetechto.podelam.data.TaskDatabase


class TaskBuilder (context: Context)
{
    init
    {
        TaskDatabase.Create(context)
    }

    private var taskName = "Задача"
    private var taskTime = "00:00"
    private var taskPlace = "Тут"
    private var taskTarget = "Там"

    fun CreateTask(_tskName : String, _tskTime : String, _tskPlace : String, _tskTarget : String) : Task
    {
        val task = Task(TaskDatabase.getLastId()+1, _tskName, _tskTime, _tskPlace, _tskTarget)
        AddTask(task)
        return task
    }

    fun AddTask(task: Task) {
        TaskDatabase.AddTaskToDB(task)
    }

    fun RemoveTask(id: Int)
    {
        TaskDatabase.RemoveTask(id)
    }

    fun EditTask()
    {
        TODO("Потом сделаю")
    }

    fun GetAllTasks() : MutableList<Task> {
        val list = mutableListOf<Task>()
        val c = TaskDatabase.GetTasks()

        val idIndex = c.getColumnIndex("_id")
        val tasknameIndex = c.getColumnIndex(TaskDatabase.TASK_NAME)
        val taskplaceIndex = c.getColumnIndex(TaskDatabase.TASK_PLACE)
        val tasktargetIndex = c.getColumnIndex(TaskDatabase.TASK_TARGET)
        val tasktimeIndex = c.getColumnIndex(TaskDatabase.TASK_TIME)
        if(c.count <= 0) return list
        c.moveToFirst()
        do {
            list.add( Task(
                c.getInt(idIndex),
                c.getString(tasknameIndex),
                c.getString(tasktimeIndex),
                c.getString(taskplaceIndex),
                c.getString(tasktargetIndex)))
            Log.e("GetAllTasks", "Получена задача с айди ${c.getInt(idIndex)} и именем ${c.getString(tasknameIndex)}")
            } while (c.moveToNext())
        return list
        }
    }