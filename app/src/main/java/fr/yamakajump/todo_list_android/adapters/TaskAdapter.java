package fr.yamakajump.todo_list_android.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.yamakajump.todo_list_android.R;
import fr.yamakajump.todo_list_android.activities.TaskDescActivity;
import fr.yamakajump.todo_list_android.activities.TaskListActivity;
import fr.yamakajump.todo_list_android.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> taskList;
    private Context context;
    private TaskListActivity taskListActivity; // Référence à l'activité

    public TaskAdapter(ArrayList<Task> taskList, TaskListActivity taskListActivity) {
        this.taskList = taskList;
        this.context = taskListActivity;
        this.taskListActivity = taskListActivity;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.descriptionTextView.setText(task.getDescription());
        holder.dateTextView.setText(task.getDate());
        holder.contextTextView.setText(task.getContext());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition(); // Obtenir la position dynamique
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, TaskDescActivity.class);
                    intent.putExtra("task", taskList.get(adapterPosition));
                    intent.putExtra("taskPosition", adapterPosition);
                    taskListActivity.startActivityForResult(intent, TaskListActivity.REQUEST_CODE_EDIT_TASK);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void updateTaskList(ArrayList<Task> newTaskList) {
        taskList.clear();
        taskList.addAll(newTaskList);
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView descriptionTextView;
        TextView dateTextView;
        TextView contextTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            contextTextView = itemView.findViewById(R.id.contextTextView);
        }
    }
}
