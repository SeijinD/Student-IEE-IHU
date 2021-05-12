package eu.seijindemon.student_iee_ihu.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_semesters.view.*

class SemestersFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_semesters, container, false)

        view.first_semester.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuSemesters)
        }

        view.second_semester.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuSemesters)
        }

        return view
    }


}