import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<MutableList<Task>>()
    val tasks: LiveData<MutableList<Task>> get() = _tasks

    init {
        _tasks.value = mutableListOf()
    }

    fun addTask(taskName: String) {
        val taskList = _tasks.value ?: mutableListOf()
        taskList.add(Task(taskList.size + 1, taskName, false))
        _tasks.value = taskList
    }

    fun toggleTaskCompletion(taskId: Int) {
        _tasks.value = _tasks.value?.map {
            if (it.id == taskId) it.copy(isCompleted = !it.isCompleted) else it
        }?.toMutableList()
    }
}
