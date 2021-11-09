package eu.seijindemon.student_iee_ihu.ui.dashboard.find.teachers.adapter

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
import eu.seijindemon.student_iee_ihu.databinding.ModelTeacherBinding
import eu.seijindemon.student_iee_ihu.framework.teacher.model.Teacher
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import www.sanju.motiontoast.MotionToast

class TeacherAdapter: RecyclerView.Adapter<TeacherAdapter.MyViewHolder>() {

    private var oldData = emptyList<Teacher>()

    class MyViewHolder(val binding: ModelTeacherBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ModelTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                holder.binding.teacherName.text = oldData[position].name_gr
                holder.binding.teacherCategory.text = oldData[position].category_gr
            }
            "en" -> {
                holder.binding.teacherName.text = oldData[position].name_en
                holder.binding.teacherCategory.text = oldData[position].category_en
            }
        }

        holder.binding.teacherEmail.text = oldData[position].email
        holder.binding.teacherPersonalSite.text = oldData[position].personal_site
        holder.binding.root.setOnClickListener{
            openLink(oldData[position].link, holder.binding.root)
        }
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

    fun setData(newData: List<Teacher>){
        oldData = newData
        notifyDataSetChanged()
    }

}