package eu.seijindemon.student_iee_ihu.ui.dashboard.guides.guide

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentSoftwareBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import eu.seijindemon.student_iee_ihu.util.Text

@AndroidEntryPoint
class SoftwareFragment : BaseFragment<FragmentSoftwareBinding>() {

    override fun getViewBinding(): FragmentSoftwareBinding {
        return FragmentSoftwareBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }

    private fun setupViews() {
        with(binding) {
            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    when(SoftwareFragmentArgs.fromBundle(requireArguments()).title) {
                        "netbeans" -> {
                            softwareTitle.text = "Netbeans"
                            softwareDescription.text = Text.netbeansGr
                        }
                    }
                }
                "en" -> {
                    when(SoftwareFragmentArgs.fromBundle(requireArguments()).title) {
                        "netbeans" -> {
                            softwareTitle.text = "Netbeans"
                            softwareDescription.text = Text.netbeansEn
                        }
                    }
                }
            }
        }
    }

}