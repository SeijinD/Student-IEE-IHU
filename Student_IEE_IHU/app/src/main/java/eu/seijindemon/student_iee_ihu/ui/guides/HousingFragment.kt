package eu.seijindemon.student_iee_ihu.ui.guides

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ms.square.android.expandabletextview.ExpandableTextView
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import eu.seijindemon.student_iee_ihu.utils.Texts
import kotlinx.android.synthetic.main.fragment_housing.view.*
import kotlinx.android.synthetic.main.text_item.view.*


class HousingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_housing, container, false)

//        val textHousing1: ExpandableTextView = view.sample1.expand_text_view
//        val textHousing2: ExpandableTextView = view.sample2.expand_text_view
//        val textHousing3: ExpandableTextView = view.sample3.expand_text_view
//
//        when(LoadLanguage.loadLanguage()) {
//            "el" -> {
//                textHousing1.text = Texts.housing1Gr
//                textHousing2.text = Texts.housing2Gr
//                textHousing3.text = Texts.housing3Gr
//            }
//            "en" -> {
//                textHousing1.text = Texts.housing1En
//                textHousing2.text = Texts.housing2En
//                textHousing3.text = Texts.housing3En
//            }
//        }

        view.more_housing.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ihu.gr/wp-content/uploads/2020/10/%CE%94%CE%99%CE%9A%CE%91%CE%99%CE%9F%CE%9B%CE%9F%CE%93%CE%97%CE%A4%CE%99%CE%9A%CE%91_%CE%95%CE%99%CE%A3%CE%91%CE%93%CE%A9%CE%93%CE%97%CE%A3_%CE%A3%CE%A4%CE%97_%CE%A6%CE%9F%CE%99%CE%A4%CE%97%CE%A4%CE%99%CE%9A%CE%97_%CE%95%CE%A3%CE%A4%CE%99%CE%91-1.pdf")))
        }

        view.site_housing.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.teithe.gr/monades/foititiki-estia/")))
        }

        return view
    }


}