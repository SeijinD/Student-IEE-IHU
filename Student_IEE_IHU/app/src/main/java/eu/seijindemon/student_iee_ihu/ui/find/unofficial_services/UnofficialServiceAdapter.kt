package eu.seijindemon.student_iee_ihu.ui.find.unofficial_services

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
import eu.seijindemon.student_iee_ihu.data.model.UnofficialService
import eu.seijindemon.student_iee_ihu.databinding.ModelUnofficialServiceBinding
import www.sanju.motiontoast.MotionToast

class UnofficialServiceAdapter: RecyclerView.Adapter<UnofficialServiceAdapter.MyViewHolder>() {

    private var oldData = emptyList<UnofficialService>()

    class MyViewHolder(val binding: ModelUnofficialServiceBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ModelUnofficialServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.unofficialServiceTitle.text = oldData[position].title
        holder.binding.unofficialServiceDescription.text = oldData[position].description
        holder.binding.unofficialServiceCreator.text = oldData[position].creator
        holder.binding.unofficialServiceCategory.text = oldData[position].category
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
                "Warning",
                "Link Not Found!",
                MotionToast.Companion.TOAST_WARNING,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(view.context, R.font.helvetica_regular))
        }
    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    fun setData(newData: List<UnofficialService>){
        oldData = newData
        notifyDataSetChanged()
    }

}