package eu.seijindemon.student_iee_ihu.ui.find.semesters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.model.Course
import kotlinx.android.synthetic.main.fragment_semester.view.*

class SemesterFragment : Fragment(), AdapterView.OnItemClickListener {

    private val courseList = ArrayList<Course>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_semester, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        when(SemesterFragmentArgs.fromBundle(requireArguments()).semesterNumber){
            "1" -> {
                toolbar.title = "First Semester"

                courseList.add(Course("Μαθηματικά Ι", "https://www.iee.ihu.gr/course/1101"))
                courseList.add(Course("Δομημένος Προγραμματισμός", "https://www.iee.ihu.gr/course/1102"))
                courseList.add(Course("Εισαγωγή στην Επιστήμη των Υπολογιστών", "https://www.iee.ihu.gr/course/1103"))
                courseList.add(Course("Ηλεκτρονική Φυσική", "https://www.iee.ihu.gr/course/1104"))
                courseList.add(Course("Κυκλώματα Συνεχούς Ρεύματος", "https://www.iee.ihu.gr/course/1105"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "2" -> {
                toolbar.title = "Second Semester"

                courseList.add(Course("Μαθηματικά ΙΙ", "https://www.iee.ihu.gr/course/1201"))
                courseList.add(Course("Μετρήσεις και Κυκλώματα Εναλλασσόμενου Ρεύματος", "https://www.iee.ihu.gr/course/1202"))
                courseList.add(Course("Τεχνική Συγγραφή, Παρουσίαση και Ορολογία Ξένης Γλώσσας", "https://www.iee.ihu.gr/course/1203"))
                courseList.add(Course("Σχεδίαση Ψηφιακών Συστημάτων", "https://www.iee.ihu.gr/course/1204"))
                courseList.add(Course("Αντικειμενοστρεφής Προγραμματισμός", "https://www.iee.ihu.gr/course/1205"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val course: Course = courseList[position]
        Navigation.findNavController(requireView()).navigate(SemesterFragmentDirections.actionMenuSemesterToCourseFragment(course.link!!))
    }


}