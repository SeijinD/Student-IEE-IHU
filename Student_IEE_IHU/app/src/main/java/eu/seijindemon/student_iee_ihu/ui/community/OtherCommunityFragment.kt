package eu.seijindemon.student_iee_ihu.ui.community

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_other_community.view.*

class OtherCommunityFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_other_community, container, false)

        view.other_community_1.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/pliroforikarioi/")))
        }

        return view
    }


}