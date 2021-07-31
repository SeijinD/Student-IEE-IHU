package eu.seijindemon.student_iee_ihu.ui.community

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
import eu.seijindemon.student_iee_ihu.data.model.Community
import eu.seijindemon.student_iee_ihu.databinding.ModelCommunityBinding
import www.sanju.motiontoast.MotionToast

class CommunityAdapter: RecyclerView.Adapter<CommunityAdapter.MyViewHolder>() {

    private var oldData = emptyList<Community>()

    class MyViewHolder(val binding: ModelCommunityBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ModelCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.communityTitle.text = oldData[position].title
        when(oldData[position].category) {
            "facebook_groups", "facebook_pages" -> {
                holder.binding.communityLogo.setBackgroundResource(R.drawable.facebook)
            }
            "discord_servers" -> {
                holder.binding.communityLogo.setBackgroundResource(R.drawable.discord)
            }
            else -> {
                holder.binding.communityLogo.setBackgroundResource(R.drawable.ic_baseline_groups_24)
            }
        }
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

    fun setData(newData: List<Community>){
        oldData = newData
        notifyDataSetChanged()
    }

}