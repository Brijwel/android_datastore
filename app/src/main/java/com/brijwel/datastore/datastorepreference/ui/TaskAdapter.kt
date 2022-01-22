package com.brijwel.datastore.datastorepreference.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brijwel.datastore.R
import com.brijwel.datastore.databinding.ItemTaskBinding
import com.brijwel.datastore.datastorepreference.model.Task

class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskViewHolder>(Task.diffUtil) {

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.task.text = task.task
            binding.completed.isChecked = task.completed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
      getItem(position)?.let {
          holder.bind(it)
      }
    }
}