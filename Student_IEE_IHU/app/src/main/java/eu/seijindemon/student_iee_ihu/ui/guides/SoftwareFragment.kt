package eu.seijindemon.student_iee_ihu.ui.guides

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import eu.seijindemon.student_iee_ihu.utils.Texts
import kotlinx.android.synthetic.main.fragment_software.view.*


class SoftwareFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_software, container, false)

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                when(SoftwareFragmentArgs.fromBundle(requireArguments()).title) {
                    "netbeans" -> {
                        view.software_title.text = "Netbeans"
                        view.software_description.text = Texts.netbeansGr
                    }
                }
            }
            "en" -> {
                when(SoftwareFragmentArgs.fromBundle(requireArguments()).title) {
                    "netbeans" -> {
                        view.software_title.text = "Netbeans"
                        view.software_description.text = Texts.netbeansEn
                    }
                }
            }
        }

        return view
    }


}