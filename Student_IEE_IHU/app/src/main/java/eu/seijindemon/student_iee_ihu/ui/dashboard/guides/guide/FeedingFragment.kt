package eu.seijindemon.student_iee_ihu.ui.dashboard.guides.guide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.ms.square.android.expandabletextview.ExpandableTextView
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentFeedingBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import eu.seijindemon.student_iee_ihu.util.Text

@AndroidEntryPoint
class FeedingFragment : BaseFragment<FragmentFeedingBinding>() {

    override fun getViewBinding(): FragmentFeedingBinding {
        return FragmentFeedingBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupListeners()

    }

    private fun setupViews() {
        with(binding) {
            val textFeeding1: ExpandableTextView = sample1.expandTextView
            val textFeeding2: ExpandableTextView = sample2.expandTextView
            val textFeeding3: ExpandableTextView = sample3.expandTextView
            val textFeeding4: ExpandableTextView = sample4.expandTextView
            val textFeeding5: ExpandableTextView = sample5.expandTextView

            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    textFeeding1.text = Text.feeding1Gr
                    textFeeding2.text = Text.feeding2Gr
                    textFeeding3.text = Text.feeding3Gr
                    textFeeding4.text = Text.feeding4Gr
                    textFeeding5.text = Text.feeding5Gr
                }
                "en" -> {
                    textFeeding1.text = Text.feeding1En
                    textFeeding2.text = Text.feeding2En
                    textFeeding3.text = Text.feeding3En
                    textFeeding4.text = Text.feeding4En
                    textFeeding5.text = Text.feeding5En
                }
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            moreFeeding.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(FeedingFragmentDirections.actionMenuFeedingToPdfWebview("http://feeding.teithe.gr/uploads/fek-1965-18-06-2012-b.pdf"))
            }

            siteFeeding.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://feeding.teithe.gr")))
            }
        }
    }

}