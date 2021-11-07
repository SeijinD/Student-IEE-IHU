package eu.seijindemon.student_iee_ihu.refactor.ui.find.official_services

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentOfficialServicesBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.refactor.ui.find.official_services.adapter.OfficialServiceAdapter

@AndroidEntryPoint
class OfficialServicesFragment : BaseFragment<FragmentOfficialServicesBinding>(), SearchView.OnQueryTextListener {

    override fun getViewBinding(): FragmentOfficialServicesBinding {
        return FragmentOfficialServicesBinding.inflate(layoutInflater)
    }

    private val viewModel: OfficialServiceViewModel by activityViewModels()

    private val officialServiceAdapter: OfficialServiceAdapter by lazy { OfficialServiceAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupViews()

    }

    private fun setupObservers() {
        with(viewModel) {
            getOfficialServices()
            readData().observe(viewLifecycleOwner) {
                officialServiceAdapter.setData(it)
            }
        }
    }

    private fun setupViews() {
        with(binding) {
            officialServicesRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            officialServicesRecyclerview.adapter = officialServiceAdapter

            searchOfficialService.isSubmitButtonEnabled = true
            searchOfficialService.setOnQueryTextListener(this@OfficialServicesFragment)
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
                officialServiceAdapter.setData(it)
            }
        })
    }


}