package com.example.studentmanagementroom

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementroom.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private var studentList = mutableListOf<Student>()
    private lateinit var appDatabase: AppDatabase
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        appDatabase = AppDatabase.getInstance(this)

        adapter = StudentAdapter(this, studentList) { student ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("studentId", student.id)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Load students asynchronously
        loadStudents()

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                searchStudents(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) // Inflate menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_student -> {
                // Khi người dùng nhấn nút "Add Student", mở màn hình thêm sinh viên
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // Load students asynchronously in the background
    private fun loadStudents() {
        CoroutineScope(Dispatchers.Main).launch {
            val students = withContext(Dispatchers.IO) {
                appDatabase.studentDao().getAllStudents() // Lấy danh sách sinh viên từ cơ sở dữ liệu
            }
            studentList.clear()
            studentList.addAll(students) // Cập nhật lại danh sách sinh viên
            adapter.notifyDataSetChanged() // Cập nhật RecyclerView
        }
    }

    // Search students asynchronously in the background
    private fun searchStudents(keyword: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val students = withContext(Dispatchers.IO) {
                appDatabase.studentDao().searchStudents("%$keyword%") // Tìm kiếm sinh viên theo tên hoặc MSSV
            }
            studentList.clear()
            studentList.addAll(students)
            adapter.notifyDataSetChanged()
        }
    }
}
