package eu.seijindemon.student_iee_ihu.ui.find.official_services

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import eu.seijindemon.student_iee_ihu.CoreApplication
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.local.viewmodel.OfficialServiceViewModel
import eu.seijindemon.student_iee_ihu.data.local.viewmodel.OfficialServiceViewModelFactory
import kotlinx.android.synthetic.main.fragment_official_services.view.*


class OfficialServicesFragment : Fragment(), SearchView.OnQueryTextListener {

    private val officialServiceViewModel: OfficialServiceViewModel by viewModels { OfficialServiceViewModelFactory((activity?.application as CoreApplication).officialServiceRepository) }

    private val officialServiceAdapter: OfficialServiceAdapter by lazy { OfficialServiceAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_official_services, container, false)

        view.official_services_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.official_services_recyclerview.adapter = officialServiceAdapter

        officialServiceViewModel.getOfficialServices()
        officialServiceViewModel.myResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    officialServiceViewModel.insertData(it)
                }
                officialServiceViewModel.readData().observe(viewLifecycleOwner) {
                    officialServiceAdapter.setData(it)
                }
            }
            else {
                Log.d("Response", response.errorBody().toString())
            }
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