package eu.seijindemon.student_iee_ihu.ui.find.courses

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
import eu.seijindemon.student_iee_ihu.data.viewmodel.CourseViewModel
import eu.seijindemon.student_iee_ihu.data.viewmodel.CourseViewModelFactory
import kotlinx.android.synthetic.main.fragment_courses.view.*


class CoursesFragment : Fragment(), SearchView.OnQueryTextListener {

    private val courseViewModel: CourseViewModel by viewModels { CourseViewModelFactory((activity?.application as CoreApplication).courseRepository) }

    private val courseAdapter: CourseAdapter by lazy { CourseAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_courses, container, false)

        view.courses_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.courses_recyclerview.adapter = courseAdapter

        courseViewModel.getCourses()
        courseViewModel.readData().observe(viewLifecycleOwner) {
            courseAdapter.setData(it)
        }

        view.search_course.isSubmitButtonEnabled = true
        view.search_course.setOnQueryTextListener(this)

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

        courseViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                courseAdapter.setData(it)
            }
        })
    }


}