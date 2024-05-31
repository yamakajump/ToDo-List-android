package fr.yamakajump.todo_list_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import fr.yamakajump.todo_list_android.R;
import fr.yamakajump.todo_list_android.models.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskCreateActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText durationEditText;
    private EditText dateEditText;
    private EditText contextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        durationEditText = findViewById(R.id.durationEditText);
        dateEditText = findViewById(R.id.dateEditText);
        contextEditText = findViewById(R.id.contextEditText);
        Button saveButton = findViewById(R.id.saveButton);

        // Set the current date as default
        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(new Date());
        dateEditText.setText(currentDate);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String durationStr = durationEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String context = contextEditText.getText().toString();

                if (title.isEmpty() || description.isEmpty() || durationStr.isEmpty() || date.isEmpty() || context.isEmpty()) {
                    Toast.makeText(TaskCreateActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                int duration;
                try {
                    duration = Integer.parseInt(durationStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(TaskCreateActivity.this, "Duration must be a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Task task = new Task(title, description, duration, date, context);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("task", task);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
