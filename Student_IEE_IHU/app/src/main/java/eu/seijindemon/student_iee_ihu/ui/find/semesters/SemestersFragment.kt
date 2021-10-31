package eu.seijindemon.student_iee_ihu.ui.find.semesters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_semesters.view.*

@AndroidEntryPoint
class SemestersFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_semesters, container, false)

        val semesterNumber = arrayListOf("1","2","3","4","5","6","7","8","9","10")

        view.first_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[0]))
        }
        view.second_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[1]))
        }
        view.third_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[2]))
        }
        view.fourth_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[3]))
        }
        view.fifth_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[4]))
        }
        view.sixth_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[5]))
        }
        view.seventh_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[6]))
        }
        view.eighth_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[7]))
        }
        view.ninth_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[8]))
        }
        view.tenth_semester.setOnClickListener{
            Navigation.findNavController(view).navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[9]))
        }

        return view
    }


}