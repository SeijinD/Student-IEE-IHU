package eu.seijindemon.student_iee_ihu.ui.community

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_facebook_pages.view.*

class FacebookPagesFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_facebook_pages, container, false)

        view.facebook_page_1.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MixanikonPliroforikisKaiIlektronikonSystimaton/")))
        }

        view.facebook_page_2.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/tei.thessalonikhs/")))
        }

        view.facebook_page_3.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/iMentor.iIHU.Sindos")))
        }

        view.facebook_page_4.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ieee.ihuthess")))
        }

        return view
    }


}