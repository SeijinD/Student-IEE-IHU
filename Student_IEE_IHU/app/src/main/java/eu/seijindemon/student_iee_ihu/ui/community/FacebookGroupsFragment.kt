package eu.seijindemon.student_iee_ihu.ui.community

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_facebook_groups.view.*

class FacebookGroupsFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_facebook_groups, container, false)

        view.facebook_group_1.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/pliroforikarioi/")))
        }

        view.facebook_group_2.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/pliroforikiserron/")))
        }

        view.facebook_group_3.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/189405949034962")))
        }

        view.facebook_group_4.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/1542744542667713")))
        }

        return view
    }


}