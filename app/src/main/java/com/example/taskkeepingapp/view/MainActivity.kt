import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskkeeping.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskAdapter = TaskAdapter(emptyList()) { taskId ->
            viewModel.toggleTaskCompletion(taskId)
        }

        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = taskAdapter

        binding.btnAddTask.setOnClickListener {
            val taskName = binding.etTask.text.toString()
            if (taskName.isNotEmpty()) {
                viewModel.addTask(taskName)
                binding.etTask.text.clear()
            }
        }

        viewModel.tasks.observe(this) { tasks ->
            taskAdapter.notifyDataSetChanged()
        }
    }
}
