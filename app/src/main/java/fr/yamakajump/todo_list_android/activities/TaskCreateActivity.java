package fr.yamakajump.todo_list_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import fr.yamakajump.todo_list_android.R;
import fr.yamakajump.todo_list_android.models.Task;

public class TaskCreateActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText durationEditText;
    private EditText dateEditText;
    private EditText contextEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        durationEditText = findViewById(R.id.durationEditText);
        dateEditText = findViewById(R.id.dateEditText);
        contextEditText = findViewById(R.id.contextEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                int duration = Integer.parseInt(durationEditText.getText().toString());
                String date = dateEditText.getText().toString();
                String context = contextEditText.getText().toString();

                Task task = new Task(title, description, duration, date, context);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("task", task);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
