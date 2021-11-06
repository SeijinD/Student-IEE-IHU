package eu.seijindemon.student_iee_ihu.refactor.ui.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentCategoryCommunityBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.refactor.ui.community.adapter.CommunityAdapter

@AndroidEntryPoint
class CategoryCommunityFragment : BaseFragment<FragmentCategoryCommunityBinding>() {

    override fun getViewBinding(): FragmentCategoryCommunityBinding {
        return FragmentCategoryCommunityBinding.inflate(layoutInflater)
    }

    private val viewModel: CommunityViewModel by activityViewModels()

    private val adapter: CommunityAdapter by lazy { CommunityAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserves()

    }

    private fun setupObserves() {
        val toolbar = binding.toolbar.root
        binding.communityRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.communityRecyclerview.adapter = adapter

        with(viewModel) {
            getCommunities()
            when (CategoryCommunityFragmentArgs.fromBundle(requireArguments()).categoryCommunity) {
                "discords_servers" -> {
                    toolbar.title = getString(R.string.discord_servers)
                    communityDiscordServers().observe(viewLifecycleOwner) { data ->
                        adapter.setData(data)
                    }
                }
                "facebook_groups" -> {
                    toolbar.title = getString(R.string.facebook_groups)
                    communityFbGroups().observe(viewLifecycleOwner) { data ->
                        adapter.setData(data)
                    }
                }
                "facebook_pages" -> {
                    toolbar.title = getString(R.string.facebook_pages)
                    communityFbPages().observe(viewLifecycleOwner) { data ->
                        adapter.setData(data)
                    }
                }
                "other_community" -> {
                    toolbar.title = getString(R.string.other_community)
                    communityOther().observe(viewLifecycleOwner) { data ->
                        adapter.setData(data)
                    }
                }
                else -> {}
            }
        }
    }

}