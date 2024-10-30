package com.example.bai3

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var editTextMSSV: EditText
    private lateinit var editTextHoTen: EditText
    private lateinit var radioGroupGioiTinh: RadioGroup
    private lateinit var editTextEmail: EditText
    private lateinit var editTextSoDienThoai: EditText
    private lateinit var calendarView: CalendarView
    private lateinit var buttonShowCalendar: Button
    private lateinit var spinnerPhuongXa: Spinner
    private lateinit var spinnerQuanHuyen: Spinner
    private lateinit var spinnerTinhThanh: Spinner
    private lateinit var checkBoxTheThao: CheckBox
    private lateinit var checkBoxDienAnh: CheckBox
    private lateinit var checkBoxAmNhac: CheckBox
    private lateinit var checkBoxDongY: CheckBox
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextMSSV = findViewById(R.id.editTextMSSV)
        editTextHoTen = findViewById(R.id.editTextHoTen)
        radioGroupGioiTinh = findViewById(R.id.radioGroupGioiTinh)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextSoDienThoai = findViewById(R.id.editTextSoDienThoai)
        calendarView = findViewById(R.id.calendarView)
        buttonShowCalendar = findViewById(R.id.buttonShowCalendar)
        spinnerPhuongXa = findViewById(R.id.spinnerPhuongXa)
        spinnerQuanHuyen = findViewById(R.id.spinnerQuanHuyen)
        spinnerTinhThanh = findViewById(R.id.spinnerTinhThanh)
        checkBoxTheThao = findViewById(R.id.checkBoxTheThao)
        checkBoxDienAnh = findViewById(R.id.checkBoxDienAnh)
        checkBoxAmNhac = findViewById(R.id.checkBoxAmNhac)
        checkBoxDongY = findViewById(R.id.checkBoxDongY)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        // Sự kiện cho nút Show Calendar
        buttonShowCalendar.setOnClickListener {
            if (calendarView.visibility == View.GONE) {
                calendarView.visibility = View.VISIBLE
            } else {
                calendarView.visibility = View.GONE
            }
        }

        // Sự kiện cho nút Submit
        buttonSubmit.setOnClickListener {
            submitForm()
        }

        // Thiết lập dữ liệu cho Spinner (các giá trị ví dụ)
        setupSpinners()
    }

    private fun setupSpinners() {
        // Dữ liệu ví dụ cho spinner
        val phuongXaList = arrayOf("Phường 1", "Phường 2", "Phường 3")
        val quanHuyenList = arrayOf("Quận 1", "Quận 2", "Quận 3")
        val tinhThanhList = arrayOf("TP. Hồ Chí Minh", "Hà Nội", "Đà Nẵng")

        val adapterPhuongXa = ArrayAdapter(this, android.R.layout.simple_spinner_item, phuongXaList)
        adapterPhuongXa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPhuongXa.adapter = adapterPhuongXa

        val adapterQuanHuyen =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, quanHuyenList)
        adapterQuanHuyen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerQuanHuyen.adapter = adapterQuanHuyen

        val adapterTinhThanh =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, tinhThanhList)
        adapterTinhThanh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTinhThanh.adapter = adapterTinhThanh
    }

    private fun submitForm() {
        val mssv = editTextMSSV.text.toString()
        val hoTen = editTextHoTen.text.toString()
        val email = editTextEmail.text.toString()
        val soDienThoai = editTextSoDienThoai.text.toString()
    }
}