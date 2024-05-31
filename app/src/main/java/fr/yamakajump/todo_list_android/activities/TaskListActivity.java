package fr.yamakajump.todo_list_android.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.yamakajump.todo_list_android.R;
import fr.yamakajump.todo_list_android.adapters.TaskAdapter;
import fr.yamakajump.todo_list_android.models.AppDatabase;
import fr.yamakajump.todo_list_android.models.Task;
import fr.yamakajump.todo_list_android.models.TaskDao;
import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_TASK = 1;
    public static final int REQUEST_CODE_EDIT_TASK = 2;
    public static final int RESULT_CODE_DELETE_TASK = 3;

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList;
    private Button addTaskButton;
    private TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskDao = AppDatabase.getInstance(this).taskDao();

        recyclerView = findViewById(R.id.recyclerView);
        addTaskButton = findViewById(R.id.addTaskButton);
        taskList = new ArrayList<>();

        taskAdapter = new TaskAdapter(taskList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        loadTasks();

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
            saveTask(newTask);
        } else if (requestCode == REQUEST_CODE_EDIT_TASK && data != null) {
            if (resultCode == RESULT_OK) {
                Task updatedTask = (Task) data.getSerializableExtra("updatedTask");
                int position = data.getIntExtra("taskPosition", -1);
                if (position != -1) {
                    updateTask(updatedTask, position);
                }
            } else if (resultCode == RESULT_CODE_DELETE_TASK) {
                int position = data.getIntExtra("taskPosition", -1);
                if (position != -1) {
                    deleteTask(position);
                }
            }
        }
    }

    private void loadTasks() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Task> tasks = taskDao.getAllTasks();
                taskList.clear();
                taskList.addAll(tasks);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taskAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void saveTask(Task task) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.insert(task);
                taskList.add(task);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taskAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void updateTask(Task task, int position) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.update(task);
                taskList.set(position, task);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taskAdapter.notifyItemChanged(position);
                    }
                });
            }
        });
    }

    private void deleteTask(int position) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.delete(taskList.get(position));
                taskList.remove(position);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taskAdapter.notifyItemRemoved(position);
                    }
                });
            }
        });
    }
}
