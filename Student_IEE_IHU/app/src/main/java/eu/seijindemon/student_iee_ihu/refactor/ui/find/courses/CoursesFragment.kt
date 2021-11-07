package eu.seijindemon.student_iee_ihu.refactor.ui.find.courses

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentCoursesBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.refactor.ui.find.courses.adapter.CourseAdapter

@AndroidEntryPoint
class CoursesFragment : BaseFragment<FragmentCoursesBinding>(), SearchView.OnQueryTextListener {

    override fun getViewBinding(): FragmentCoursesBinding {
        return FragmentCoursesBinding.inflate(layoutInflater)
    }

    private val viewModel: CourseViewModel by activityViewModels()

    private val courseAdapter: CourseAdapter by lazy { CourseAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupViews()

    }

    private fun setupObservers() {
        with(viewModel) {
            getCourses()
            readData().observe(viewLifecycleOwner) {
                courseAdapter.setData(it)
            }
        }
    }

    private fun setupViews() {
        with(binding) {
            coursesRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            coursesRecyclerview.adapter = courseAdapter

            searchCourse.isSubmitButtonEnabled = true
            searchCourse.setOnQueryTextListener(this@CoursesFragment)
        }
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

        viewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                courseAdapter.setData(it)
            }
        })
    }


}