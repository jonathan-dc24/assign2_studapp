package com.example.studentapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentapp.models.Student;
import com.example.studentapp.repository.StudentRepository;
import com.google.android.material.textfield.TextInputEditText;

public class AddStudentActivity extends AppCompatActivity {

    private TextInputEditText editTextId;
    private TextInputEditText editTextName;
    private TextInputEditText editTextPhone;
    private TextInputEditText editTextAddress;
    private Button buttonSave;
    private Button buttonCancel;
    private StudentRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        repository = StudentRepository.getInstance();

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(v -> saveStudent());
        buttonCancel.setOnClickListener(v -> finish());
    }

    private void saveStudent() {
        String id = editTextId.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if (id.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "ID and Name are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (repository.getById(id) != null) {
            Toast.makeText(this, "Student with this ID already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        Student newStudent = new Student(id, name, phone, address);
        repository.add(newStudent);

        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
