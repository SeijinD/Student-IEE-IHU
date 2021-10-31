package eu.seijindemon.student_iee_ihu.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import eu.seijindemon.student_iee_ihu.utils.Texts
import kotlinx.android.synthetic.main.fragment_about.view.*

@AndroidEntryPoint
class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        val aboutDescription = view.description

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                aboutDescription.text = Texts.aboutDescriptionGr
            }
            "en" -> {
                aboutDescription.text = Texts.aboutDescriptionEn
            }
        }

        return view
    }

}