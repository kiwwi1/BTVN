package com.example.studentmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class AddStudentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        val nameEditText: EditText = view.findViewById(R.id.editTextName)
        val mssvEditText: EditText = view.findViewById(R.id.editTextMSSV)
        val saveButton: Button = view.findViewById(R.id.buttonSave)

        // Lấy dữ liệu truyền vào (nếu có) từ arguments
        val arguments = arguments
        val name = arguments?.getString("name")
        val mssv = arguments?.getString("mssv")

        // Nếu có dữ liệu cũ, điền vào các EditText
        if (name != null && mssv != null) {
            nameEditText.setText(name)
            mssvEditText.setText(mssv)
        }

        saveButton.setOnClickListener {
            val newName = nameEditText.text.toString()
            val newMSSV = mssvEditText.text.toString()

            // Trả dữ liệu về StudentListFragment
            val resultBundle = Bundle().apply {
                putString("name", newName)
                putString("mssv", newMSSV)
            }
            setFragmentResult("studentRequestKey", resultBundle)

            // Quay lại danh sách
            findNavController().popBackStack()
        }

        return view
    }
}





