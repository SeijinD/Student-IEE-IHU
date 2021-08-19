package eu.seijindemon.student_iee_ihu.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.textview.MaterialTextView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.Texts

class HomeAdapter(var context: Context, var language: String) : RecyclerView.Adapter<HomeAdapter.MyHolder>(){

    private var homeTextsEn = arrayOf<String>(
        Texts.home1En,
        Texts.home2En,
        Texts.home3En,
        Texts.home4En
    )

    private var homeTextsGr = arrayOf<String>(
        Texts.home1Gr,
        Texts.home2Gr,
        Texts.home3Gr,
        Texts.home4Gr
    )

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: MaterialTextView = itemView.findViewById(R.id.text_view) as MaterialTextView
//        var container: RelativeLayout = itemView.findViewById(R.id.container) as RelativeLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return  MyHolder(LayoutInflater.from(context).inflate(R.layout.item_page, parent, false))
    }

    override fun getItemCount(): Int {
        return if (language == "en") {
            homeTextsEn.size
        } else {
            homeTextsGr.size
        }
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (language == "en") {
            holder.textView.text = homeTextsEn[position]
        }
        else {
            holder.textView.text = homeTextsGr[position]
        }
//        holder.container.setBackgroundResource(colorTextMatrix[position][0])
    }



}