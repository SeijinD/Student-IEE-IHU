package eu.seijindemon.student_iee_ihu.ui.community

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import eu.seijindemon.student_iee_ihu.CoreApplication
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.local.viewmodel.CommunityViewModel
import eu.seijindemon.student_iee_ihu.data.local.viewmodel.CommunityViewModelFactory
import eu.seijindemon.student_iee_ihu.data.model.Community
import kotlinx.android.synthetic.main.fragment_category_community.view.*

class CategoryCommunityFragment : Fragment() {

    private val communityViewModel: CommunityViewModel by viewModels { CommunityViewModelFactory((activity?.application as CoreApplication).communityRepository) }

    private val communityAdapter: CommunityAdapter by lazy { CommunityAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category_community, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        view.community_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.community_recyclerview.adapter = communityAdapter

        communityViewModel.getCommunities()
        communityViewModel.myResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    communityViewModel.insertData(it)
                }
            }
            else {
                Log.d("Response", response.errorBody().toString())
            }
        }

//        val community = Community("Facebook 1", "facebook_pages", "https://www.seijind.eu")
//        communityViewModel.insertData(community)
//        val community2 = Community("Facebook 2", "facebook_pages", "https://www.seijind.eu")
//        communityViewModel.insertData(community2)
//        val community3 = Community("Discord 1", "discord_servers", "https://www.seijind.eu")
//        communityViewModel.insertData(community3)

        when(CategoryCommunityFragmentArgs.fromBundle(requireArguments()).categoryCommunity) {
            "discords_servers" -> {
                toolbar.title = "Discord Servers"
                communityViewModel.communityDiscordServers().observe(viewLifecycleOwner) { data ->
                    communityAdapter.setData(data)
                }
            }
            "facebook_groups" -> {
                toolbar.title = "Facebook Groups"
                communityViewModel.communityFbGroups().observe(viewLifecycleOwner) { data ->
                    communityAdapter.setData(data)
                }
            }
            "facebook_pages" -> {
                toolbar.title = "Facebook Pages"
                communityViewModel.communityFbPages().observe(viewLifecycleOwner) { data ->
                    communityAdapter.setData(data)
                }
            }
            "other_community" -> {
                toolbar.title = "Other Community"
                communityViewModel.communityOther().observe(viewLifecycleOwner) { data ->
                    communityAdapter.setData(data)
                }
            }
        }

        return view
    }


}