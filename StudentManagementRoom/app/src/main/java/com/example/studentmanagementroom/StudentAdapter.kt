package com.example.studentmanagementroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementroom.databinding.ItemStudentBinding

class StudentAdapter(
    private val context: Context,
    private var studentList: List<Student>,
    private val onItemClickListener: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = studentList[position]
        holder.mssvTextView.text = student.mssv
        holder.hotenTextView.text = student.hoten

        holder.itemView.setOnClickListener { onItemClickListener(student) }
    }

    override fun getItemCount(): Int = studentList.size

    fun setStudentList(studentList: List<Student>) {
        this.studentList = studentList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mssvTextView: TextView = itemView.findViewById(R.id.mssvTextView)
        val hotenTextView: TextView = itemView.findViewById(R.id.hotenTextView)
    }
}

