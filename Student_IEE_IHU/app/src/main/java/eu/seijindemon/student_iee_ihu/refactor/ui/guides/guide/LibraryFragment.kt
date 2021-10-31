package eu.seijindemon.student_iee_ihu.refactor.ui.guides.guide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.ms.square.android.expandabletextview.ExpandableTextView
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentLibraryBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import eu.seijindemon.student_iee_ihu.util.Text

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {

    override fun getViewBinding(): FragmentLibraryBinding {
        return FragmentLibraryBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupListeners()

    }

    private fun setupViews() {
        with(binding) {
            val textLibrary1: ExpandableTextView = sample1.expandTextView
            val textLibrary2: ExpandableTextView = sample2.expandTextView
            val textLibrary3: ExpandableTextView = sample3.expandTextView

            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    textLibrary1.text = Text.library1Gr
                    textLibrary2.text = Text.library2Gr
                    textLibrary3.text = Text.library3Gr
                }
                "en" -> {
                    textLibrary1.text = Text.library1En
                    textLibrary2.text = Text.library2En
                    textLibrary3.text = Text.library3En
                }
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            moreLibrary.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lib.teithe.gr/faq")))
            }

            siteLibrary.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lib.teithe.gr")))
            }
        }
    }

}