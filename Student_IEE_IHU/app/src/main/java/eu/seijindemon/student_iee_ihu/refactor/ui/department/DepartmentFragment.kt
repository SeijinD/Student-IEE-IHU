package eu.seijindemon.student_iee_ihu.refactor.ui.department

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentDepartmentBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import eu.seijindemon.student_iee_ihu.util.Text

@AndroidEntryPoint
class DepartmentFragment : BaseFragment<FragmentDepartmentBinding>() {

    override fun getViewBinding(): FragmentDepartmentBinding {
        return FragmentDepartmentBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }

    private fun setupViews() {
        with(binding) {
            val departmentText1 = departmentText1
            val departmentText2 = departmentText2

            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    departmentText1.text = Text.departmentText1Gr
                    departmentText2.text = Text.departmentText2Gr
                }
                "en" -> {
                    departmentText1.text = Text.departmentText1En
                    departmentText2.text = Text.departmentText2En
                }
            }
        }
    }

}