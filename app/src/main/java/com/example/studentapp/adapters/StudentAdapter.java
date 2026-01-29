package com.example.studentapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studentapp.R;
import com.example.studentapp.models.Student;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> students;
    private OnStudentClickListener listener;

    public interface OnStudentClickListener {
        void onStudentClick(Student student);
        void onEditClick(Student student);
    }

    public StudentAdapter(List<Student> students, OnStudentClickListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void updateStudents(List<Student> newStudents) {
        this.students = newStudents;
        notifyDataSetChanged();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewId;
        private ImageButton buttonEdit;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewId = itemView.findViewById(R.id.textViewId);
            buttonEdit = itemView.findViewById(R.id.buttonEditStudent);
        }

        public void bind(Student student) {
            textViewName.setText(student.getName());
            textViewId.setText(student.getId());

            buttonEdit.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEditClick(student);
                }
            });

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onStudentClick(student);
                }
            });
        }
    }
}
