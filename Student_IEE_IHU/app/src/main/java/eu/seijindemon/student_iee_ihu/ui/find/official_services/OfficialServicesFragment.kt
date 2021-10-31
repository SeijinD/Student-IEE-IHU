package eu.seijindemon.student_iee_ihu.ui.find.official_services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_official_services.view.*

@AndroidEntryPoint
class OfficialServicesFragment : Fragment(), SearchView.OnQueryTextListener {

    private val officialServiceViewModel: OfficialServiceViewModel by activityViewModels()

    private val officialServiceAdapter: OfficialServiceAdapter by lazy { OfficialServiceAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_official_services, container, false)

        view.official_services_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.official_services_recyclerview.adapter = officialServiceAdapter

        officialServiceViewModel.getOfficialServices()
        officialServiceViewModel.readData().observe(viewLifecycleOwner) {
            officialServiceAdapter.setData(it)
        }

        view.search_official_service.isSubmitButtonEnabled = true
        view.search_official_service.setOnQueryTextListener(this)

        return view
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

        officialServiceViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                officialServiceAdapter.setData(it)
            }
        })
    }


}