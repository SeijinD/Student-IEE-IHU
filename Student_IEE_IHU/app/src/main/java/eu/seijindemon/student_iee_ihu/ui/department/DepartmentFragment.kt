package eu.seijindemon.student_iee_ihu.ui.department

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import eu.seijindemon.student_iee_ihu.utils.Texts
import kotlinx.android.synthetic.main.fragment_department.view.*

class DepartmentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_department, container, false)

        val departmentText1 = view.department_text_1
        val departmentText2 = view.department_text_2

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                departmentText1.text = Texts.departmentText1Gr
                departmentText2.text = Texts.departmentText2Gr
            }
            "en" -> {
                departmentText1.text = Texts.departmentText1En
                departmentText2.text = Texts.departmentText2En
            }
        }


        return view
    }


}