package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studentapp.adapters.StudentAdapter;
import com.example.studentapp.models.Student;
import com.example.studentapp.repository.StudentRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private FloatingActionButton fabAddStudent;
    private StudentRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = StudentRepository.getInstance();

        recyclerView = findViewById(R.id.recyclerViewStudents);
        fabAddStudent = findViewById(R.id.fabAddStudent);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StudentAdapter(repository.getAll(), student -> {
            Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
            intent.putExtra("STUDENT_ID", student.getId());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        fabAddStudent.setOnClickListener(v -> {
            Toast.makeText(this, "Add Student screen coming in next step", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateStudents(repository.getAll());
    }
}
