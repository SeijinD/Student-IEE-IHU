package eu.seijindemon.student_iee_ihu.ui.guides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import eu.seijindemon.student_iee_ihu.utils.Texts
import kotlinx.android.synthetic.main.fragment_first_day.*


class FirstDayFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first_day, container, false)

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                more_firstday.text = Texts.firstDay1Gr
            }
            "en" -> {
                more_firstday.text = Texts.firstDay1En
            }
        }

        return view
    }


}