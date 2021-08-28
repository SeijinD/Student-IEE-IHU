package eu.seijindemon.student_iee_ihu.ui.guides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.textview.MaterialTextView
import com.ms.square.android.expandabletextview.ExpandableTextView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import eu.seijindemon.student_iee_ihu.utils.Texts
import kotlinx.android.synthetic.main.fragment_feeding.*
import kotlinx.android.synthetic.main.fragment_feeding.view.*
import kotlinx.android.synthetic.main.text_item.view.*


class FeedingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feeding, container, false)

        val textFeeding1: ExpandableTextView = view.sample1.expand_text_view
        val textFeeding2: ExpandableTextView = view.sample2.expand_text_view
        val textFeeding3: ExpandableTextView = view.sample3.expand_text_view
        val textFeeding4: ExpandableTextView = view.sample4.expand_text_view
        val textFeeding5: ExpandableTextView = view.sample5.expand_text_view

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                textFeeding1.text = Texts.feeding1Gr
                textFeeding2.text = Texts.feeding2Gr
                textFeeding3.text = Texts.feeding3Gr
                textFeeding4.text = Texts.feeding4Gr
                textFeeding5.text = Texts.feeding5Gr
            }
            "en" -> {
                textFeeding1.text = Texts.feeding1En
                textFeeding2.text = Texts.feeding2En
                textFeeding3.text = Texts.feeding3En
                textFeeding4.text = Texts.feeding4En
                textFeeding5.text = Texts.feeding5En
            }
        }

        view.moreFeeding.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(FeedingFragmentDirections.actionMenuFeedingToPdfWebview("http://feeding.teithe.gr/uploads/fek-1965-18-06-2012-b.pdf"))
        }

        return view
    }


}