package eu.seijindemon.student_iee_ihu.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.seijindemon.student_iee_ihu.R

class SemesterFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_semester, container, false)

        val semester_number = SemesterFragmentArgs.fromBundle(requireArguments()).semesterNumber

        when(semester_number){
            "1" -> {

            }
            "2" -> {

            }
            "3" -> {

            }
            "4" -> {

            }
            "5" -> {

            }
            "6" -> {

            }
            "7" -> {

            }
            "8" -> {

            }
            "9" -> {

            }
            "10" -> {

            }
        }

        return view
    }


}