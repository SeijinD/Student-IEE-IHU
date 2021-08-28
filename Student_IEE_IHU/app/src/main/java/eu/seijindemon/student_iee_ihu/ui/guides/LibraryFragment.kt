package eu.seijindemon.student_iee_ihu.ui.guides

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ms.square.android.expandabletextview.ExpandableTextView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import eu.seijindemon.student_iee_ihu.utils.Texts
import kotlinx.android.synthetic.main.fragment_library.view.*
import kotlinx.android.synthetic.main.text_item.view.*


class LibraryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        val textLibrary1: ExpandableTextView = view.sample1.expand_text_view
        val textLibrary2: ExpandableTextView = view.sample2.expand_text_view
        val textLibrary3: ExpandableTextView = view.sample3.expand_text_view

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                textLibrary1.text = Texts.library1Gr
                textLibrary2.text = Texts.library2Gr
                textLibrary3.text = Texts.library3Gr
            }
            "en" -> {
                textLibrary1.text = Texts.library1En
                textLibrary2.text = Texts.library2En
                textLibrary3.text = Texts.library3En
            }
        }

        view.more_library.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lib.teithe.gr/faq")))
        }

        view.site_library.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lib.teithe.gr")))
        }

        return view
    }


}