package eu.seijindemon.student_iee_ihu.ui.find.teachers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import eu.seijindemon.student_iee_ihu.databinding.TeacherModelBinding

class TeacherAdapter: RecyclerView.Adapter<TeacherAdapter.MyViewHolder>() {

    private var oldData = emptyList<Teacher>()

    class MyViewHolder(val binding: TeacherModelBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TeacherModelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.teacherName.text = oldData[position].name
    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    fun setData(newData: List<Teacher>){
        oldData = newData
        notifyDataSetChanged()
    }

}