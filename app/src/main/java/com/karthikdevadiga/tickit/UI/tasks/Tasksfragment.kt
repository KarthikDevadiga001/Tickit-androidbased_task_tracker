package com.karthikdevadiga.tickit.UI.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.karthikdevadiga.tickit.data.Task
import com.karthikdevadiga.tickit.data.TaskDao
import com.karthikdevadiga.tickit.data.TickitDatabase
import com.karthikdevadiga.tickit.databinding.FragmentTasksBinding
import kotlin.concurrent.thread

class Tasksfragment: Fragment(), TasksAdapter.TaskUpdatedListner {

    private val viewmodel:TaskViewModel by viewModels()
    private lateinit var binding:FragmentTasksBinding
    val taskviewModel: TaskViewModel by viewModels()
    private val taskDao:TaskDao by lazy {
        TickitDatabase.createDatabase(requireContext()).getTaskDao()
    }

    private val adapter=TasksAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentTasksBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter =adapter
        fetchAllTasks()
    }

    fun fetchAllTasks() {
        thread {
            val tasks = taskDao.getAllTasks()
            requireActivity().runOnUiThread {
                adapter.setTasks(tasks)
            }
        }
    }

    override fun onTaskUpdated(task: Task) {
        thread {
            taskDao.editask(task)
            fetchAllTasks()
        }
    }

}