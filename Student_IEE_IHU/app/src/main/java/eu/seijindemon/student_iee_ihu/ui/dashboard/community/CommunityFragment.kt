package eu.seijindemon.student_iee_ihu.ui.dashboard.community

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentCommunityBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {

    override fun getViewBinding(): FragmentCommunityBinding {
        return FragmentCommunityBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            discords.setOnClickListener {
                findNavController().navigate(CommunityFragmentDirections.actionMenuCommunityToCategoryCommunity("discords_servers"))
            }

            facebookGroups.setOnClickListener {
                findNavController().navigate(CommunityFragmentDirections.actionMenuCommunityToCategoryCommunity("facebook_groups"))
            }

            facebookPages.setOnClickListener {
                findNavController().navigate(CommunityFragmentDirections.actionMenuCommunityToCategoryCommunity("facebook_pages"))
            }

            otherCommunity.setOnClickListener {
                findNavController().navigate(CommunityFragmentDirections.actionMenuCommunityToCategoryCommunity("other_community"))
            }
        }
    }

}