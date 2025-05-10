package com.karthikdevadiga.tickit.UI.tasks

import androidx.activity.viewModels
import com.karthikdevadiga.tickit.UI.MainViewModel
import kotlin.concurrent.thread

class TaskViewModel {

    val taskdao=TickitApplication.taskdao

    fun fetchtasks(){
        thread {
            val tasks = taskdao.getAllTasks()
            requireActivity().runOnUiThread {
                adapter.setTasks(tasks)
            }
        }

    }

    fun fetchAlltasks(){

    }

    fun updateTasks(){

    }
}