package com.brijwel.datastore.datastorepreference.model

import androidx.recyclerview.widget.DiffUtil

data class Task(
    val task: String,
    val completed: Boolean = false
) {
    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem.task == newItem.task

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem == newItem

        }

        fun getTasks(): List<Task> {
            return arrayListOf<Task>().apply {
                add(
                    Task(
                        "Learn Compose"
                    )
                )
                add(
                    Task(
                        "Learn Coroutine", true
                    )
                )
                add(
                    Task(
                        "Learn Web development"
                    )
                )
                add(
                    Task(
                        "Learn Kotlin",
                        true
                    )
                )
                add(
                    Task(
                        "Make Friends"
                    )
                )
                add(
                    Task(
                        "Travel"
                    )
                )
                add(
                    Task(
                        "Draw a sketch"
                    )
                )
                add(
                    Task(
                        "Be Kind",
                        true
                    )
                )

            }
        }
    }
}
