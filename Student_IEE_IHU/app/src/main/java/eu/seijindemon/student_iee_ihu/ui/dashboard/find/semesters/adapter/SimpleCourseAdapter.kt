package eu.seijindemon.student_iee_ihu.ui.dashboard.find.semesters.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.framework.model.SimpleCourse

class SimpleCourseAdapter(private val context: Context, private val dataSet: ArrayList<SimpleCourse>) : BaseAdapter(){

    override fun getCount(): Int {
        return dataSet.size
    }

    override fun getItem(position: Int): Any {
        return dataSet[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.model_simple_course, parent, false)

        val courseTitle = view.findViewById<TextView>(R.id.course_title)
        courseTitle.text = dataSet[position].title

        return view
    }


}