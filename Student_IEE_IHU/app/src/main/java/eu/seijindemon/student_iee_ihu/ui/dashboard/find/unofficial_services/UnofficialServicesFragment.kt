package eu.seijindemon.student_iee_ihu.ui.dashboard.find.unofficial_services

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentUnofficialServicesBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.ui.dashboard.find.unofficial_services.adapter.UnofficialServiceAdapter

@AndroidEntryPoint
class UnofficialServicesFragment : BaseFragment<FragmentUnofficialServicesBinding>(), SearchView.OnQueryTextListener {

    override fun getViewBinding(): FragmentUnofficialServicesBinding {
        return FragmentUnofficialServicesBinding.inflate(layoutInflater)
    }

    private val viewModel: UnofficialServiceViewModel by activityViewModels()

    private val unofficialServiceAdapter: UnofficialServiceAdapter by lazy { UnofficialServiceAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupViews()

    }

    private fun setupObservers() {
        with(viewModel) {
            getUnofficialServices()
            readData().observe(viewLifecycleOwner) {
                unofficialServiceAdapter.setData(it)
            }
        }
    }

    private fun setupViews() {
        with(binding) {
            unofficialServiceRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            unofficialServiceRecyclerview.adapter = unofficialServiceAdapter

            searchUnofficialService.isSubmitButtonEnabled = true
            searchUnofficialService.setOnQueryTextListener(this@UnofficialServicesFragment)
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
                unofficialServiceAdapter.setData(it)
            }
        })
    }


}