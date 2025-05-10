package com.karthikdevadiga.tickit.UI

import TickitApplication
import com.karthikdevadiga.tickit.data.Task
import kotlin.concurrent.thread

class MainViewModel {

    val taskdao=TickitApplication.taskdao

    fun createtask(title:String , details: String ){

        val task= Task(
            title=title,
            description = details
        )

        thread {
            taskdao.createTask(task)
        }
    }

}