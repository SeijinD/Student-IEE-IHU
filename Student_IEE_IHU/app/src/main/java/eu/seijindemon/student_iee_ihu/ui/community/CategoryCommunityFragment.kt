package eu.seijindemon.student_iee_ihu.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_category_community.view.*

@AndroidEntryPoint
class CategoryCommunityFragment : Fragment() {

    private val communityViewModel: CommunityViewModel by activityViewModels()

    private val communityAdapter: CommunityAdapter by lazy { CommunityAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category_community, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        view.community_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.community_recyclerview.adapter = communityAdapter

        communityViewModel.getCommunities()

        when(CategoryCommunityFragmentArgs.fromBundle(requireArguments()).categoryCommunity) {
            "discords_servers" -> {
                toolbar.title = getString(R.string.discord_servers)
                communityViewModel.communityDiscordServers().observe(viewLifecycleOwner) { data ->
                    communityAdapter.setData(data)
                }
            }
            "facebook_groups" -> {
                toolbar.title = getString(R.string.facebook_groups)
                communityViewModel.communityFbGroups().observe(viewLifecycleOwner) { data ->
                    communityAdapter.setData(data)
                }
            }
            "facebook_pages" -> {
                toolbar.title = getString(R.string.facebook_pages)
                communityViewModel.communityFbPages().observe(viewLifecycleOwner) { data ->
                    communityAdapter.setData(data)
                }
            }
            "other_community" -> {
                toolbar.title = getString(R.string.other_community)
                communityViewModel.communityOther().observe(viewLifecycleOwner) { data ->
                    communityAdapter.setData(data)
                }
            }
        }

        return view
    }


}