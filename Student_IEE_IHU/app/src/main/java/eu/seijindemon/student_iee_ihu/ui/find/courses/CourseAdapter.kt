package eu.seijindemon.student_iee_ihu.ui.find.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.seijindemon.student_iee_ihu.data.model.Course
import eu.seijindemon.student_iee_ihu.databinding.ModelCourseBinding

class CourseAdapter: RecyclerView.Adapter<CourseAdapter.MyViewHolder>() {

    private var oldData = emptyList<Course>()

    class MyViewHolder(val binding: ModelCourseBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ModelCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.courseTitle.text = oldData[position].title
        holder.binding.courseSemester.text = oldData[position].semester
        holder.binding.courseTeachers.text = oldData[position].teachers
    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    fun setData(newData: List<Course>){
        oldData = newData
        notifyDataSetChanged()
    }

}