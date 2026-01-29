package com.example.studentapp.repository;

import com.example.studentapp.models.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static StudentRepository instance;
    private ArrayList<Student> students;

    private StudentRepository() {
        students = new ArrayList<>();
    }

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    public void add(Student student) {
        students.add(student);
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }

    public Student getById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public boolean update(String oldId, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(oldId)) {
                students.set(i, updatedStudent);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        return students.size();
    }
}
