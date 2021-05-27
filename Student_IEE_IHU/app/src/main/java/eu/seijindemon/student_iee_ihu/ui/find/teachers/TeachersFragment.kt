package eu.seijindemon.student_iee_ihu.ui.find.teachers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import eu.seijindemon.student_iee_ihu.CoreApplication
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.local.viewmodel.TeacherViewModel
import eu.seijindemon.student_iee_ihu.data.local.viewmodel.TeacherViewModelFactory
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import kotlinx.android.synthetic.main.fragment_teachers.view.*


class TeachersFragment : Fragment(), SearchView.OnQueryTextListener {

    private val teacherViewModel: TeacherViewModel by viewModels { TeacherViewModelFactory((activity?.application as CoreApplication).teacherRepository) }

    private val teacherAdapter: TeacherAdapter by lazy { TeacherAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_teachers, container, false)

        view.teachers_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.teachers_recyclerview.adapter = teacherAdapter

//        val teacher = Teacher("Manabis Giwrgos", "georgekara@yahoo.gr", "https://www.seijind.eu", "Kurios")
//        teacherViewModel.insertData(teacher)

        teacherViewModel.readData.observe(viewLifecycleOwner) {
            teacherAdapter.setData(it)
        }

        view.search_teacher.isSubmitButtonEnabled = true
        view.search_teacher.setOnQueryTextListener(this)

        return view
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchDatabase(newText)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        teacherViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                teacherAdapter.setData(it)
            }
        })
    }

}