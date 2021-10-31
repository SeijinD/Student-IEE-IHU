package eu.seijindemon.student_iee_ihu.ui.guides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_softwares.view.*

@AndroidEntryPoint
class SoftwaresFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_softwares, container, false)

        view.netbeans.setOnClickListener {
            Navigation.findNavController(view).navigate(SoftwaresFragmentDirections.actionMenuSoftwaresToMenuSoftware("netbeans"))
        }


        return view
    }


}