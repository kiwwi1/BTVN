import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class FragmentAddEdit : Fragment() {

    private lateinit var editTextName: EditText
    private lateinit var editTextMSSV: EditText
    private var position: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit, container, false)
        editTextName = view.findViewById(R.id.editTextName)
        editTextMSSV = view.findViewById(R.id.editTextMSSV)

        val bundle = arguments
        if (bundle != null) {
            editTextName.setText(bundle.getString("name"))
            editTextMSSV.setText(bundle.getString("mssv"))
            position = bundle.getInt("position", -1)
        }

        view.findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val name = editTextName.text.toString()
            val mssv = editTextMSSV.text.toString()
            val bundleResult = Bundle().apply {
                putString("name", name)
                putString("mssv", mssv)
                putInt("position", position)
            }
            parentFragmentManager.setFragmentResult("student_result", bundleResult)
            findNavController().navigateUp()
        }

        return view
    }
}
