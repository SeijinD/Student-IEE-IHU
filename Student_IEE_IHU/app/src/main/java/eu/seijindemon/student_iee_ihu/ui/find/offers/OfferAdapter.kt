package eu.seijindemon.student_iee_ihu.ui.find.offers

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
import eu.seijindemon.student_iee_ihu.data.model.Offer
import eu.seijindemon.student_iee_ihu.databinding.ModelOfferBinding
import www.sanju.motiontoast.MotionToast

class OfferAdapter: RecyclerView.Adapter<OfferAdapter.MyViewHolder>() {

    private var oldData = emptyList<Offer>()

    class MyViewHolder(val binding: ModelOfferBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ModelOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.offerTitle.text = oldData[position].title
        holder.binding.offerDescription.text = oldData[position].description
        holder.binding.offerCategory.text = oldData[position].category
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

    fun setData(newData: List<Offer>){
        oldData = newData
        notifyDataSetChanged()
    }

}