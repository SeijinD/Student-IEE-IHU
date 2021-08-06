package eu.seijindemon.student_iee_ihu.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ms.square.android.expandabletextview.ExpandableTextView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.Texts
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import kotlinx.android.synthetic.main.fragment_help.view.*
import kotlinx.android.synthetic.main.text_item.view.*

class HelpFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        val textHelp1: ExpandableTextView = view.sample1.expand_text_view
        val textHelp2: ExpandableTextView = view.sample2.expand_text_view
        val textHelp3: ExpandableTextView = view.sample3.expand_text_view
        val textHelp4: ExpandableTextView = view.sample4.expand_text_view

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                textHelp1.text = Texts.helpText1Gr
                textHelp2.text = Texts.helpText2Gr
                textHelp3.text = Texts.helpText3Gr
                textHelp4.text = Texts.helpText4Gr
            }
            "en" -> {
                textHelp1.text = Texts.helpText1En
                textHelp2.text = Texts.helpText2En
                textHelp3.text = Texts.helpText3En
                textHelp4.text = Texts.helpText4En
            }
        }

        return view
    }


}