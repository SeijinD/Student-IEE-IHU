package eu.seijindemon.student_iee_ihu.nav_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_community.view.*

class CommunityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_community, container, false)

        view.discords.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuDiscords)
        }

        view.facebook_groups.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuFacebookGroups)
        }

        view.facebook_pages.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuFacebookPages)
        }

        view.other_community.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuOtherCommunity)
        }

        return view
    }


}