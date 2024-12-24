package com.example.studentmanagementroom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.studentmanagementroom.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var mssvEditText: EditText
    private lateinit var hotenEditText: EditText
    private lateinit var ngaysinhEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var appDatabase: AppDatabase
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mssvEditText = findViewById(R.id.mssvEditText)
        hotenEditText = findViewById(R.id.hotenEditText)
        ngaysinhEditText = findViewById(R.id.ngaysinhEditText)
        emailEditText = findViewById(R.id.emailEditText)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)

        appDatabase = AppDatabase.getInstance(this)

        // Lấy dữ liệu từ Intent
        val studentId = intent.getIntExtra("studentId", -1)
        student = appDatabase.studentDao().getStudentById(studentId) ?: return

        mssvEditText.setText(student.mssv)
        hotenEditText.setText(student.hoten)
        ngaysinhEditText.setText(student.ngaysinh)
        emailEditText.setText(student.email)

        updateButton.setOnClickListener {
            student = student.copy(
                mssv = mssvEditText.text.toString(),
                hoten = hotenEditText.text.toString(),
                ngaysinh = ngaysinhEditText.text.toString(),
                email = emailEditText.text.toString()
            )
            appDatabase.studentDao().update(student)
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
        }

        deleteButton.setOnClickListener {
            appDatabase.studentDao().delete(student)
            Toast.makeText(this, "Xóa sinh viên thành công", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
