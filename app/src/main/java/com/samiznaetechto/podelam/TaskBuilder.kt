package com.samiznaetechto.podelam

import android.content.Context
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
        val task = Task(_tskName, _tskTime, _tskPlace, _tskTarget)
        AddTask(task)
        return task
    }

    fun AddTask(task: Task) {
        TaskDatabase.AddTaskToDB(task)
    }

    fun RemoveTask()
    {
        TODO("Потом сделаю")
    }

    fun EditTask()
    {
        TODO("Потом сделаю")
    }

    fun GetAllTasks() : MutableList<Task> {
        var list = mutableListOf<Task>()
        var c = TaskDatabase.GetTasks()

        var idIndex = c.getColumnIndex("_id")
        var tasknameIndex = c.getColumnIndex(TaskDatabase.TASK_NAME)
        var taskplaceIndex = c.getColumnIndex(TaskDatabase.TASK_PLACE)
        var tasktargetIndex = c.getColumnIndex(TaskDatabase.TASK_TARGET)
        var tasktimeIndex = c.getColumnIndex(TaskDatabase.TASK_TIME)
        c.moveToFirst()
        do {
            list.add( Task(
                c.getString(tasknameIndex),
                c.getString(tasktimeIndex),
                c.getString(taskplaceIndex),
                c.getString(tasktargetIndex)))
            } while (c.moveToNext())
        return list
        }
    }