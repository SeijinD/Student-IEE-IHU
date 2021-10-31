package eu.seijindemon.student_iee_ihu.ui.guides

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_thesis.view.*

@AndroidEntryPoint
class ThesisFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_thesis, container, false)

//        val textThesis1: ExpandableTextView = view.sample1.expand_text_view
//        val textThesis2: ExpandableTextView = view.sample2.expand_text_view
//        val textThesis3: ExpandableTextView = view.sample3.expand_text_view
//
//        when(LoadLanguage.loadLanguage()) {
//            "el" -> {
//                textThesis1.text = Texts.thesis1Gr
//                textThesis2.text = Texts.thesis2Gr
//                textThesis3.text = Texts.thesis3Gr
//            }
//            "en" -> {
//                textThesis1.text = Texts.thesis1En
//                textThesis2.text = Texts.thesis2En
//                textThesis3.text = Texts.thesis3En
//            }
//        }

        view.more_thesis.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iee.ihu.gr/wp-content/uploads/2021/03/%CE%9A%CE%B1%CE%BD%CE%BF%CE%BD%CE%B9%CF%83%CE%BC%CF%8C%CF%82-%CE%94%CE%B9%CF%80%CE%BB%CF%89%CE%BC%CE%B1%CF%84%CE%B9%CE%BA%CF%8E%CE%BD-%CE%95%CF%81%CE%B3%CE%B1%CF%83%CE%B9%CF%8E%CE%BD-R1.pdf")))
        }

        view.site_thesis.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iee.ihu.gr/course/1999")))
        }

        return view
    }


}