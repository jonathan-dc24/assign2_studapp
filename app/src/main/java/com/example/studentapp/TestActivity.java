package com.example.studentapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentapp.models.Student;
import com.example.studentapp.repository.StudentRepository;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StudentRepository repo = StudentRepository.getInstance();

        repo.add(new Student("001", "John Doe", "123-456-7890", "123 Main St"));
        repo.add(new Student("002", "Jane Smith", "098-765-4321", "456 Oak Ave"));

        Log.d(TAG, "Total students: " + repo.getCount());
        for (Student student : repo.getAll()) {
            Log.d(TAG, "Student: " + student.getName() + " (ID: " + student.getId() + ")");
        }

        Student student = repo.getById("001");
        if (student != null) {
            Log.d(TAG, "Found student: " + student.getName());
        }

        Student updated = new Student("001", "John Updated", "111-111-1111", "999 New St");
        repo.update("001", updated);
        Log.d(TAG, "After update: " + repo.getById("001").getName());

        repo.delete("002");
        Log.d(TAG, "After delete: " + repo.getCount() + " students remaining");
    }
}
