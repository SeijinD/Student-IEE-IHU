package eu.seijindemon.student_iee_ihu.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ms.square.android.expandabletextview.ExpandableTextView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.HelpTexts
import kotlinx.android.synthetic.main.fragment_help.view.*
import kotlinx.android.synthetic.main.fragment_help.view.sample1
import kotlinx.android.synthetic.main.fragment_help.view.sample2
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.text_item.view.*

class HelpFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        val textHelp1: ExpandableTextView = view.sample1.expand_text_view
        textHelp1.text = HelpTexts.helpText1

        val textHelp2: ExpandableTextView = view.sample2.expand_text_view
        textHelp2.text = HelpTexts.helpText2

        return view
    }


}