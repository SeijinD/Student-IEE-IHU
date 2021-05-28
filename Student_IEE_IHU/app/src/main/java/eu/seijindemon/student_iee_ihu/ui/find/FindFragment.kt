package eu.seijindemon.student_iee_ihu.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_find.view.*

class FindFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_find, container, false)

        view.semesters.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuSemesters)
        }

        view.courses.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuCourses)
        }

        view.teachers.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuTeachers)
        }

        view.maps.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuMaps)
        }

        return view
    }


}