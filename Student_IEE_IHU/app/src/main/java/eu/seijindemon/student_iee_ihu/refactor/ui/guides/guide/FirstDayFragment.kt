package eu.seijindemon.student_iee_ihu.refactor.ui.guides.guide

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentFirstDayBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import eu.seijindemon.student_iee_ihu.util.Text

@AndroidEntryPoint
class FirstDayFragment : BaseFragment<FragmentFirstDayBinding>() {

    override fun getViewBinding(): FragmentFirstDayBinding {
        return FragmentFirstDayBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }

    private fun setupViews() {
        with(binding) {
            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    moreFirstday.text = Text.firstDay1Gr
                }
                "en" -> {
                    moreFirstday.text = Text.firstDay1En
                }
            }
        }
    }

}