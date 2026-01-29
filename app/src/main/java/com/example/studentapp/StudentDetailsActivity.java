package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentapp.models.Student;
import com.example.studentapp.repository.StudentRepository;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView textViewId;
    private TextView textViewName;
    private TextView textViewPhone;
    private TextView textViewAddress;
    private TextView textViewChecked;
    private Button buttonEdit;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        textViewId = findViewById(R.id.textViewId);
        textViewName = findViewById(R.id.textViewName);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewChecked = findViewById(R.id.textViewChecked);
        buttonEdit = findViewById(R.id.buttonEdit);

        studentId = getIntent().getStringExtra("STUDENT_ID");

        if (studentId != null) {
            loadStudentDetails();
        } else {
            Toast.makeText(this, "Error: Student ID not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        buttonEdit.setOnClickListener(v -> {
            Toast.makeText(this, "Edit screen coming in Step 4", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (studentId != null) {
            loadStudentDetails();
        }
    }

    private void loadStudentDetails() {
        StudentRepository repository = StudentRepository.getInstance();
        Student student = repository.getById(studentId);

        if (student != null) {
            textViewId.setText(student.getId());
            textViewName.setText(student.getName());
            textViewPhone.setText(student.getPhone());
            textViewAddress.setText(student.getAddress());
            textViewChecked.setText(student.isChecked() ? getString(R.string.yes) : getString(R.string.no));
        } else {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
