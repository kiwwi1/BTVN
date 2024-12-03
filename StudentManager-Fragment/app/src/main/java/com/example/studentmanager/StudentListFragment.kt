package com.example.studentmanager

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val studentList = mutableListOf<Pair<String, String>>(
        "Nguyen Van A" to "1001",
        "Tran Thi B" to "1002",
        "Le Van C" to "1003",
        "Pham Thi D" to "1004",
        "Hoang Van E" to "1005",
        "Dang Thi F" to "1006",
        "Vu Van G" to "1007",
        "Nguyen Thi H" to "1008",
        "Tran Van I" to "1009",
        "Le Thi J" to "1010",
        "Pham Van K" to "1011",
        "Hoang Thi L" to "1012",
        "Dang Van M" to "1013",
        "Vu Thi N" to "1014",
        "Nguyen Van O" to "1015",
        "Tran Thi P" to "1016",
        "Le Van Q" to "1017",
        "Pham Thi R" to "1018",
        "Hoang Van S" to "1019",
        "Dang Thi T" to "1020"
        // Thêm sinh viên khác nếu cần
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        listView = view.findViewById(R.id.listViewStudents)
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            studentList.map { "${it.first} - ${it.second}" }
        )
        listView.adapter = adapter

        registerForContextMenu(listView)

        // Lắng nghe dữ liệu từ AddStudentFragment
        setFragmentResultListener("studentRequestKey") { _, result ->
            val name = result.getString("name")
            val mssv = result.getString("mssv")

            if (!name.isNullOrEmpty() && !mssv.isNullOrEmpty()) {
                // Cập nhật danh sách
                val existingIndex = studentList.indexOfFirst { it.second == mssv }
                if (existingIndex != -1) {
                    // Chỉnh sửa sinh viên đã có
                    studentList[existingIndex] = name to mssv
                } else {
                    // Thêm sinh viên mới
                    studentList.add(name to mssv)
                }
                updateList()
            }
        }

        return view
    }


    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.action_edit -> {
                // Điều hướng đến màn hình chỉnh sửa sinh viên với Bundle
                val selectedStudent = studentList[info.position]
                val bundle = Bundle().apply {
                    putString("name", selectedStudent.first)
                    putString("mssv", selectedStudent.second)
                }
                findNavController().navigate(R.id.action_studentList_to_addStudent, bundle)
                return true
            }
            R.id.action_remove -> {
                // Xóa sinh viên khỏi danh sách
                studentList.removeAt(info.position)
                updateList()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun updateList() {
        // Cập nhật adapter sau khi chỉnh sửa hoặc xóa
        adapter.clear()
        adapter.addAll(studentList.map { "${it.first} - ${it.second}" })
        adapter.notifyDataSetChanged()
    }
}
