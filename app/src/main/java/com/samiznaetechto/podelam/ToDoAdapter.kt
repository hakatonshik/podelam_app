package com.samiznaetechto.podelam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter (
    var todos : List<Task>
    ) : RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            var taskName = findViewById<TextView>(R.id.TaskNameTV)
            var taskOrigin = findViewById<TextView>(R.id.tvTaskOrigin)
            var taskTime = findViewById<TextView>(R.id.tvTaskTime)
            var taskTarget = findViewById<TextView>(R.id.tvTaskTarget)
            var removeBtn = findViewById<TextView>(R.id.btnRemoveTask)
            removeBtn.setOnClickListener {
                val tb = TaskBuilder(context)
                tb.RemoveTask(todos[position].id)
                notifyItemChanged(position)
            }
            taskName.text = todos[position].taskName
            taskOrigin.text = todos[position].taskPlace
            taskTarget.text = todos[position].taskTarget
            taskTime.text = todos[position].taskTime
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = todos.size

}