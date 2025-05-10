package com.karthikdevadiga.tickit.UI

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.karthikdevadiga.tickit.UI.tasks.Tasksfragment
import com.karthikdevadiga.tickit.data.Task
import com.karthikdevadiga.tickit.data.TaskDao
import com.karthikdevadiga.tickit.data.TickitDatabase
import com.karthikdevadiga.tickit.databinding.ActivityMainBinding
import com.karthikdevadiga.tickit.databinding.AddtaskDialogBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private val  tasksfragment=Tasksfragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{
            pager.adapter = PagerAdapter(this@MainActivity)

            setContentView(root)
            TabLayoutMediator(tabs, pager) { tab, _ ->
                tab.text = "Tasks"
            }.attach()

            fab.setOnClickListener {
                showAddTaskDialog()
            }
        }
    }

    private fun showAddTaskDialog() {

        AddtaskDialogBinding.inflate(layoutInflater).apply{
            val dialog = BottomSheetDialog(this@MainActivity)
            dialog.setContentView(root)


            buttonShowDetails.setOnClickListener {
                editTextTaskdetails.visibility =
                    if (editTextTaskdetails.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }

            saveButton.setOnClickListener {
                viewModel.createtask(editTextTasktitle.text.toString(),editTextTaskdetails.text.toString())
                dialog.dismiss()
                tasksfragment.fetchAllTasks()
            }

            dialog.show()

        }

    }

    inner class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount() = 1

        override fun createFragment(position: Int): Fragment {
            return tasksfragment
        }
    }


}