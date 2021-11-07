package eu.seijindemon.student_iee_ihu.refactor.ui.find.courses.adapter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.ModelCourseBinding
import eu.seijindemon.student_iee_ihu.framework.course.model.Course
import eu.seijindemon.student_iee_ihu.util.BASE_URL_SITE_EL
import eu.seijindemon.student_iee_ihu.util.BASE_URL_SITE_EN
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import www.sanju.motiontoast.MotionToast

class CourseAdapter: RecyclerView.Adapter<CourseAdapter.MyViewHolder>() {

    private var oldData = emptyList<Course>()

    class MyViewHolder(val binding: ModelCourseBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ModelCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                holder.binding.courseTitle.text = oldData[position].title_gr
                holder.binding.courseTeachers.text = oldData[position].teachers_gr
                holder.binding.root.setOnClickListener{
                    openLink(BASE_URL_SITE_EL + oldData[position].link, holder.binding.root)
                }
            }
            "en" -> {
                holder.binding.courseTitle.text = oldData[position].title_en
                holder.binding.courseTeachers.text = oldData[position].teachers_en
                holder.binding.root.setOnClickListener{
                    openLink(BASE_URL_SITE_EN + oldData[position].link, holder.binding.root)
                }
            }
        }

        holder.binding.courseSemester.text = oldData[position].semester

    }

    private fun openLink(link: String?, view: View)
    {
        val uri: Uri = Uri.parse(link)
        try {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            view.context.startActivity(intent)

        } catch (e: ActivityNotFoundException) {
            MotionToast.Companion.createColorToast(
                view.context as Activity,
                view.context.getString(R.string.warning),
                view.context.getString(R.string.link_not_found),
                MotionToast.Companion.TOAST_WARNING,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(view.context, R.font.helvetica_regular))
        }
    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    fun setData(newData: List<Course>){
        oldData = newData
        notifyDataSetChanged()
    }

}