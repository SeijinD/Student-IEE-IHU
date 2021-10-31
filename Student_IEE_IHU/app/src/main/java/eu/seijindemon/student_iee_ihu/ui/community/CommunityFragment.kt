package eu.seijindemon.student_iee_ihu.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_community.view.*

@AndroidEntryPoint
class CommunityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_community, container, false)

        view.discords.setOnClickListener {
            Navigation.findNavController(view).navigate(CommunityFragmentDirections.actionMenuCommunityToCategoryCommunity("discords_servers"))
        }

        view.facebook_groups.setOnClickListener {
            Navigation.findNavController(view).navigate(CommunityFragmentDirections.actionMenuCommunityToCategoryCommunity("facebook_groups"))
        }

        view.facebook_pages.setOnClickListener {
            Navigation.findNavController(view).navigate(CommunityFragmentDirections.actionMenuCommunityToCategoryCommunity("facebook_pages"))
        }

        view.other_community.setOnClickListener {
            Navigation.findNavController(view).navigate(CommunityFragmentDirections.actionMenuCommunityToCategoryCommunity("other_community"))
        }

        return view
    }


}