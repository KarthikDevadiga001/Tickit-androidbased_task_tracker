package com.karthikdevadiga.tickit.UI.tasks

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.karthikdevadiga.tickit.data.Task
import com.karthikdevadiga.tickit.databinding.ItemTaskBinding

class TasksAdapter(private val listener: TaskUpdatedListner) :

    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    private var tasks: List<Task> = listOf()

//  private lateinit var updatedTask: Task
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks (tasks:List<Task>){

        val sortedTasks=tasks.sortedBy {
            it.iscompleted
        }
        this.tasks=sortedTasks
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {

            binding.apply {
                checkbox.isChecked = task.iscompleted
                toggleStar.isChecked = task.isStarred

                if (task.iscompleted) {
                    textViewTitle.paintFlags =
                        textViewTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    textViewDetails.paintFlags =
                        textViewTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                }else{
                    textViewDetails.paintFlags=0
                    textViewTitle.paintFlags=0
                }
                textViewTitle.text = task.title
                if(task.description.isNullOrEmpty()){
                    textViewDetails.visibility=View.GONE
                }else{
                    textViewDetails.text = task.description
                    textViewDetails.visibility=View.VISIBLE

                }


                checkbox.setOnClickListener {
                    val updatedTask =task.copy(iscompleted = checkbox.isChecked)
                    listener.onTaskUpdated(updatedTask)
                }

                toggleStar.setOnClickListener {
                    val updatedTask =task.copy(isStarred = toggleStar.isChecked)
                    listener.onTaskUpdated(updatedTask)

                }
            }

        }
    }

    interface TaskUpdatedListner {
        fun onTaskUpdated(task: Task)
    }
}



