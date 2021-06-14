package eu.seijindemon.student_iee_ihu.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.model.Community
import eu.seijindemon.student_iee_ihu.databinding.ModelCommunityBinding

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
    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    fun setData(newData: List<Community>){
        oldData = newData
        notifyDataSetChanged()
    }

}