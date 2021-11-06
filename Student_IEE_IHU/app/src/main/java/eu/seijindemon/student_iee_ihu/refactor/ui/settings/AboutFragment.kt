package eu.seijindemon.student_iee_ihu.refactor.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentAboutBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import eu.seijindemon.student_iee_ihu.util.Text
import kotlinx.android.synthetic.main.fragment_about.view.*

@AndroidEntryPoint
class AboutFragment : BaseFragment<FragmentAboutBinding>() {

    override fun getViewBinding(): FragmentAboutBinding {
        return FragmentAboutBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }

    fun setupViews() {
        with(binding) {
            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    description.text = Text.aboutDescriptionGr
                }
                "en" -> {
                    description.text = Text.aboutDescriptionEn
                }
            }
        }
    }

}