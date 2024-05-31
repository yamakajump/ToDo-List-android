package fr.yamakajump.todo_list_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.yamakajump.todo_list_android.R;
import fr.yamakajump.todo_list_android.models.Task;

public class TaskDescActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText durationEditText;
    private EditText dateEditText;
    private EditText contextEditText;
    private int taskPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_desc);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        durationEditText = findViewById(R.id.durationEditText);
        dateEditText = findViewById(R.id.dateEditText);
        contextEditText = findViewById(R.id.contextEditText);
        Button saveButton = findViewById(R.id.saveButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button retourButton = findViewById(R.id.retourButton);

        Task task = (Task) getIntent().getSerializableExtra("task");
        taskPosition = getIntent().getIntExtra("taskPosition", -1);

        if (task != null) {
            titleEditText.setText(task.getTitle());
            descriptionEditText.setText(task.getDescription());
            durationEditText.setText(String.valueOf(task.getDuration()));
            dateEditText.setText(task.getDate());
            contextEditText.setText(task.getContext());
        }

        // Set current date if date field is empty
        if (dateEditText.getText().toString().isEmpty()) {
            String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(new Date());
            dateEditText.setText(currentDate);
        }

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String durationStr = durationEditText.getText().toString();
            String date = dateEditText.getText().toString();
            String context = contextEditText.getText().toString();

            if (title.isEmpty() || description.isEmpty() || durationStr.isEmpty() || date.isEmpty() || context.isEmpty()) {
                Toast.makeText(TaskDescActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            int duration;
            try {
                duration = Integer.parseInt(durationStr);
            } catch (NumberFormatException e) {
                Toast.makeText(TaskDescActivity.this, "Duration must be a number", Toast.LENGTH_SHORT).show();
                return;
            }

            Task updatedTask = new Task(title, description, duration, date, context);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedTask", updatedTask);
            resultIntent.putExtra("taskPosition", taskPosition);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Add the delete button click listener
        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("taskPosition", taskPosition);
            setResult(TaskListActivity.RESULT_CODE_DELETE_TASK, resultIntent);
            finish();
        });

        retourButton.setOnClickListener(v -> onBackPressed());
    }
}
