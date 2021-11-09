package eu.seijindemon.student_iee_ihu.ui.dashboard.help

import android.os.Bundle
import android.view.View
import com.ms.square.android.expandabletextview.ExpandableTextView
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentHelpBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import eu.seijindemon.student_iee_ihu.util.Text

@AndroidEntryPoint
class HelpFragment : BaseFragment<FragmentHelpBinding>() {

    override fun getViewBinding(): FragmentHelpBinding {
        return FragmentHelpBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }

    private fun setupViews() {
        with(binding) {
            val textHelp1: ExpandableTextView = sample1.expandTextView
            val textHelp2: ExpandableTextView = sample2.expandTextView
            val textHelp3: ExpandableTextView = sample3.expandTextView
            val textHelp4: ExpandableTextView = sample4.expandTextView
            val textHelp5: ExpandableTextView = sample5.expandTextView
            val textHelp6: ExpandableTextView = sample6.expandTextView
            val textHelp7: ExpandableTextView = sample7.expandTextView

            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    textHelp1.text = Text.helpText1Gr
                    textHelp2.text = Text.helpText2Gr
                    textHelp3.text = Text.helpText3Gr
                    textHelp4.text = Text.helpText4Gr
                    textHelp5.text = Text.helpText5Gr
                    textHelp6.text = Text.helpText6Gr
                    textHelp7.text = Text.helpText7Gr
                }
                "en" -> {
                    textHelp1.text = Text.helpText1En
                    textHelp2.text = Text.helpText2En
                    textHelp3.text = Text.helpText3En
                    textHelp4.text = Text.helpText4En
                    textHelp5.text = Text.helpText5En
                    textHelp6.text = Text.helpText6En
                    textHelp7.text = Text.helpText7En
                }
            }
        }
    }

}