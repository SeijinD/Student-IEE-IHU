package eu.seijindemon.student_iee_ihu.refactor.ui.find.teachers

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentTeachersBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.refactor.ui.find.teachers.adapter.TeacherAdapter

@AndroidEntryPoint
class TeachersFragment : BaseFragment<FragmentTeachersBinding>(), SearchView.OnQueryTextListener {

    override fun getViewBinding(): FragmentTeachersBinding {
        return FragmentTeachersBinding.inflate(layoutInflater)
    }

    private val viewModel: TeacherViewModel by activityViewModels()

    private val teacherAdapter: TeacherAdapter by lazy { TeacherAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupViews()

    }

    private fun setupObservers() {
        with(viewModel) {
            getTeachers()
            readData().observe(viewLifecycleOwner) {
                teacherAdapter.setData(it)
            }
        }
    }

    private fun setupViews() {
        with(binding) {
            teachersRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            teachersRecyclerview.adapter = teacherAdapter

            searchTeacher.isSubmitButtonEnabled = true
            searchTeacher.setOnQueryTextListener(this@TeachersFragment)
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
                teacherAdapter.setData(it)
            }
        })
    }

}