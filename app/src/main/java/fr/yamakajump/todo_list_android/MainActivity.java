package fr.yamakajump.todo_list_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import fr.yamakajump.todo_list_android.activities.TaskListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Démarrer TaskListActivity au démarrage de l'application
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
        finish(); // Pour éviter que l'utilisateur revienne à cette activité en appuyant sur le bouton retour
    }
}