import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var taskEditText: EditText
    private lateinit var taskListView: ListView
    private lateinit var tasks: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskEditText = findViewById(R.id.taskEditText)
        taskListView = findViewById(R.id.taskListView)

        tasks = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks)
        taskListView.adapter = adapter

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val task = taskEditText.text.toString().trim()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyDataSetChanged()
                taskEditText.text.clear()
            }
        }

        val deleteButton = findViewById<Button>(R.id.deleteButton)
        deleteButton.setOnClickListener {
            val selectedItemPosition = taskListView.checkedItemPosition
            if (selectedItemPosition != ListView.INVALID_POSITION) {
                tasks.removeAt(selectedItemPosition)
                adapter.notifyDataSetChanged()
            }
        }

        val completeButton = findViewById<Button>(R.id.completeButton)
        completeButton.setOnClickListener {
            val selectedItemPosition = taskListView.checkedItemPosition
            if (selectedItemPosition != ListView.INVALID_POSITION) {
                tasks[selectedItemPosition] = tasks[selectedItemPosition] + " (Completed)"
                adapter.notifyDataSetChanged()
            }
        }
    }
}
