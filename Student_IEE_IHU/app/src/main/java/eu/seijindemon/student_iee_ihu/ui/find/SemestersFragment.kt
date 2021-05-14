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

        val semester_number = arrayListOf("1","2","3","4","5","6","7","8","9","10")

        view.first_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semester_number[0]))
        }
        view.second_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semester_number[1]))
        }

        return view
    }


}