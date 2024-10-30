package com.example.bai1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var inputNumber: EditText
    private lateinit var evenButton: RadioButton
    private lateinit var oddButton: RadioButton
    private lateinit var squareButton: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var listViewResult: ListView
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.inputNumber)
        evenButton = findViewById(R.id.even)
        oddButton = findViewById(R.id.odd)
        squareButton = findViewById(R.id.square)
        buttonShow = findViewById(R.id.showButton)
        listViewResult = findViewById(R.id.listview)
        errorText = findViewById(R.id.textView2)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        val input = inputNumber.text.toString()
        errorText.text = " "

        // Kiểm tra dữ liệu nhập vào
        if (input.isEmpty()) {
            errorText.text = "Vui lòng nhập 1 số nguyên dương"
            return
        }

        val n = input.toIntOrNull()
        if (n == null || n < 1) {
            errorText.text = "Vui lòng nhập 1 số nguyên dương"
            return
        }

        val resultList = mutableListOf<Int>()
        when {
            evenButton.isChecked -> {
                for (i in 0..n step 2) {
                    resultList.add(i)
                }
            }
            oddButton.isChecked -> {
                for (i in 1..n step 2) {
                    resultList.add(i)
                }
            }
            squareButton.isChecked -> {
                var i = 0
                while (i * i <= n) {
                    resultList.add(i * i)
                    i++
                }
            }
            else -> {
                errorText.text = "Vui lòng chọn loại số"
                return
            }
        }

        // Hiển thị kết quả trong ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
        listViewResult.adapter = adapter
    }
}
