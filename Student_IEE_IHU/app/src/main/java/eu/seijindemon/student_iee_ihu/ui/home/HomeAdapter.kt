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

class HomeAdapter(var context: Context) : RecyclerView.Adapter<HomeAdapter.MyHolder>(){

    private var colorTextMatrix = arrayOf<IntArray>(
        intArrayOf(R.string.about_app),
        intArrayOf(R.string.admin_page),
        intArrayOf(R.string.app_name),
        intArrayOf(R.string.apps)
    )

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: MaterialTextView = itemView.findViewById(R.id.text_view) as MaterialTextView
//        var container: RelativeLayout = itemView.findViewById(R.id.container) as RelativeLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return  MyHolder(LayoutInflater.from(context).inflate(R.layout.item_page, parent, false))
    }

    override fun getItemCount(): Int {
        return colorTextMatrix.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.textView.setText(colorTextMatrix[position][0])
//        holder.container.setBackgroundResource(colorTextMatrix[position][0])
    }



}