package com.example.bai2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bai2.Student
import com.example.bai2.StudentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        recyclerView = findViewById(R.id.recyclerView)

        // Danh sách sinh viên mẫu
        studentList = listOf(
            Student("Nguyen Van A", "123456"),
            Student("Tran Thi B", "654321"),
            Student("Le Van C", "111111"),
            Student("Pham Thi D", "222222"),
            Student("Hoang Van E", "333333"),
            Student("Nguyen Thi F", "444444"),
            Student("Tran Van G", "555555"),
            Student("Le Thi H", "666666"),
            Student("Pham Van I", "777777"),
            Student("Hoang Thi J", "888888"),
            Student("Nguyen Van K", "999999"),
            Student("Dang Van L", "101010"),
            Student("Vu Thi M", "202020"),
            Student("Nguyen Van N", "303030"),
            Student("Phan Thi O", "404040"),
            Student("Le Van P", "505050"),
            Student("Tran Thi Q", "606060"),
            Student("Nguyen Van R", "707070"),
            Student("Hoang Thi S", "808080"),
            Student("Pham Van T", "909090"),
            Student("Bui Thi U", "010101")
        )


        // Thiết lập Adapter cho RecyclerView
        adapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Lắng nghe thay đổi trong ô tìm kiếm
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    // Hàm tìm kiếm và lọc danh sách
    private fun filter(text: String) {
        val filteredList = studentList.filter {
            it.name.contains(text, ignoreCase = true) || it.mssv.contains(text, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }
}

