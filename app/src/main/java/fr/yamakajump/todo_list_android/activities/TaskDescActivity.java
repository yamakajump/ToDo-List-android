package fr.yamakajump.todo_list_android.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fr.yamakajump.todo_list_android.R;
import fr.yamakajump.todo_list_android.models.Task;

public class TaskDescActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView durationTextView;
    private TextView dateTextView;
    private TextView contextTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_desc);

        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        durationTextView = findViewById(R.id.durationTextView);
        dateTextView = findViewById(R.id.dateTextView);
        contextTextView = findViewById(R.id.contextTextView);

        Task task = (Task) getIntent().getSerializableExtra("task");

        if (task != null) {
            titleTextView.setText(task.getTitle());
            descriptionTextView.setText(task.getDescription());
            durationTextView.setText(String.valueOf(task.getDuration()));
            dateTextView.setText(task.getDate());
            contextTextView.setText(task.getContext());
        }
    }
}
