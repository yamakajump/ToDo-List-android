package fr.yamakajump.todo_list_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.yamakajump.todo_list_android.R;
import fr.yamakajump.todo_list_android.adapters.TaskAdapter;
import fr.yamakajump.todo_list_android.models.Task;
import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_TASK = 1;

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList;
    private Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        recyclerView = findViewById(R.id.recyclerView);
        addTaskButton = findViewById(R.id.addTaskButton);
        taskList = new ArrayList<>();

        // Temporarily add some sample tasks
        taskList.add(new Task("Task 1", "Description 1", 30, "30 mai 2024", "Home"));
        taskList.add(new Task("Task 2", "Description 2", 60, "30 mai 2024", "Office"));

        taskAdapter = new TaskAdapter(taskList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, TaskCreateActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_TASK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == RESULT_OK && data != null) {
            Task newTask = (Task) data.getSerializableExtra("task");
            taskList.add(newTask);
            taskAdapter.notifyDataSetChanged();
        }
    }
}
