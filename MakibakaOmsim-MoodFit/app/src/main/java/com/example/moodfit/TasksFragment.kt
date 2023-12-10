package com.example.moodfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.CheckBox
import android.widget.TextView
import com.example.moodfit.databinding.FragmentTasksBinding

// Add the Task data class
data class Task(val description: String, var isCompleted: Boolean)

// Add the TasksAdapter class
class TasksAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.taskCheckBox)
        val descriptionTextView: TextView = view.findViewById(R.id.taskDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.checkBox.isChecked = task.isCompleted
        holder.descriptionTextView.text = task.description
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Initialize the RecyclerView with the TasksAdapter and a list of sample tasks
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sampleTasks = listOf(
            Task("Listen to Uplifting Music", false),
            Task("Practice Gratitude", true),
            Task("Take a Nature Walk", true),
            Task("Engage in Physical Activity", true),
            Task("Connect with Loved Ones", true),
            Task("Try a Relaxation Technique", true),
            Task("Indulge in a Hobby", true),
            Task("Treat Yourself to a Favorite Snack", true),
            Task("Watch a Feel-Good Movie or TV Show", true),
            Task("Set and Achieve a Small Goal", false)
        )
        binding.tasksRecyclerView.adapter = TasksAdapter(sampleTasks)
        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}