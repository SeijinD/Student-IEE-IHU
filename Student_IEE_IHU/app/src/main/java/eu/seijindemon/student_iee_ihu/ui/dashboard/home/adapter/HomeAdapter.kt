package eu.seijindemon.student_iee_ihu.ui.dashboard.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.util.Text

class HomeAdapter(var context: Context, var language: String) : RecyclerView.Adapter<HomeAdapter.MyHolder>(){

    private var homeTextsEn = arrayOf<String>(
        Text.home1En,
        Text.home2En,
        Text.home3En,
        Text.home4En
    )

    private var homeTextsGr = arrayOf<String>(
        Text.home1Gr,
        Text.home2Gr,
        Text.home3Gr,
        Text.home4Gr
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