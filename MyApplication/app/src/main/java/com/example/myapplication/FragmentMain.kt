import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class FragmentMain : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val studentList = mutableListOf<Pair<String, String>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        listView = view.findViewById(R.id.listViewStudents)

        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            studentList.map { "${it.first} - ${it.second}" }
        )
        listView.adapter = adapter

        // Context menu
        registerForContextMenu(listView)

        // OptionMenu
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add) {
            findNavController().navigate(R.id.action_to_addEditFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.action_edit -> {
                val bundle = Bundle().apply {
                    putString("name", studentList[info.position].first)
                    putString("mssv", studentList[info.position].second)
                    putInt("position", info.position)
                }
                findNavController().navigate(R.id.action_to_addEditFragment, bundle)
            }
            R.id.action_remove -> {
                studentList.removeAt(info.position)
                updateList()
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun updateList() {
        adapter.clear()
        adapter.addAll(studentList.map { "${it.first} - ${it.second}" })
        adapter.notifyDataSetChanged()
    }


}
