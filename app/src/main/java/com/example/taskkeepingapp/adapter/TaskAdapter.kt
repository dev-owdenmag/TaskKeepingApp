import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskkeepingapp.databinding.ItemTaskBinding

class TaskAdapter(
    private val tasks: List<Task>,
    private val onTaskToggle: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.tvTaskName.text = task.name
        holder.binding.checkboxCompleted.isChecked = task.isCompleted

        holder.binding.checkboxCompleted.setOnCheckedChangeListener { _, _ ->
            onTaskToggle(task.id)
        }
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)
}
