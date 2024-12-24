package com.example.studentmanagementroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<Student>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int): Student?

    @Query("SELECT * FROM student WHERE mssv LIKE :keyword OR hoten LIKE :keyword")
    fun searchStudents(keyword: String): List<Student>
}


