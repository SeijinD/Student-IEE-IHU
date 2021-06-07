package eu.seijindemon.student_iee_ihu.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_help.view.*

class HelpFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        val helpInfo = HelpFragmentArgs.fromBundle(requireArguments()).helpInfo

        view.help_info.text = helpInfo

        return view
    }


}