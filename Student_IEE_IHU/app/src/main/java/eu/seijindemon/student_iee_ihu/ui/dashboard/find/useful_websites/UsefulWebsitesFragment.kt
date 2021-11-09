package eu.seijindemon.student_iee_ihu.ui.dashboard.find.useful_websites

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentUsefulWebsitesBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.ui.dashboard.find.useful_websites.adapter.UsefulWebsiteAdapter

@AndroidEntryPoint
class UsefulWebsitesFragment : BaseFragment<FragmentUsefulWebsitesBinding>(), SearchView.OnQueryTextListener {

    override fun getViewBinding(): FragmentUsefulWebsitesBinding {
        return FragmentUsefulWebsitesBinding.inflate(layoutInflater)
    }

    private val viewModel: UsefulWebsiteViewModel by activityViewModels()

    private val usefulWebsiteAdapter: UsefulWebsiteAdapter by lazy { UsefulWebsiteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupViews()

    }

    private fun setupObservers() {
        with(viewModel) {
            getUsefulWebsites()
            readData().observe(viewLifecycleOwner) {
                usefulWebsiteAdapter.setData(it)
            }
        }
    }

    private fun setupViews() {
        with(binding) {
            usefulWebsitesRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            usefulWebsitesRecyclerview.adapter = usefulWebsiteAdapter

            searchUsefulWebsite.isSubmitButtonEnabled = true
            searchUsefulWebsite.setOnQueryTextListener(this@UsefulWebsitesFragment)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchDatabase(newText)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                usefulWebsiteAdapter.setData(it)
            }
        })
    }


}