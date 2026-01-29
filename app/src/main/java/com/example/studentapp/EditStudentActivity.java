package com.example.studentapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentapp.models.Student;
import com.example.studentapp.repository.StudentRepository;
import com.google.android.material.textfield.TextInputEditText;

public class EditStudentActivity extends AppCompatActivity {

    private TextInputEditText editTextId;
    private TextInputEditText editTextName;
    private TextInputEditText editTextPhone;
    private TextInputEditText editTextAddress;
    private CheckBox checkBoxChecked;
    private Button buttonSave;
    private Button buttonDelete;
    private Button buttonCancel;
    private String originalStudentId;
    private StudentRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        repository = StudentRepository.getInstance();

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        checkBoxChecked = findViewById(R.id.checkBoxChecked);
        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonCancel = findViewById(R.id.buttonCancel);

        originalStudentId = getIntent().getStringExtra("STUDENT_ID");

        if (originalStudentId != null) {
            loadStudentData();
        } else {
            Toast.makeText(this, "Error: Student ID not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        buttonSave.setOnClickListener(v -> saveStudent());
        buttonDelete.setOnClickListener(v -> deleteStudent());
        buttonCancel.setOnClickListener(v -> finish());
    }

    private void loadStudentData() {
        Student student = repository.getById(originalStudentId);
        if (student != null) {
            editTextId.setText(student.getId());
            editTextName.setText(student.getName());
            editTextPhone.setText(student.getPhone());
            editTextAddress.setText(student.getAddress());
            checkBoxChecked.setChecked(student.isChecked());
        } else {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void saveStudent() {
        String id = editTextId.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        boolean checked = checkBoxChecked.isChecked();

        if (id.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "ID and Name are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Student updatedStudent = new Student(id, name, phone, address);
        updatedStudent.setChecked(checked);

        boolean success = repository.update(originalStudentId, updatedStudent);
        if (success) {
            Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent() {
        boolean success = repository.delete(originalStudentId);
        if (success) {
            Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
        }
    }
}
