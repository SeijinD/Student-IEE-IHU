package eu.seijindemon.student_iee_ihu.ui.find.unofficial_services

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
import kotlinx.android.synthetic.main.fragment_unofficial_services.view.*

@AndroidEntryPoint
class UnofficialServicesFragment : Fragment(), SearchView.OnQueryTextListener {

    private val unofficialServiceViewModel: UnofficialServiceViewModel by activityViewModels()

    private val unofficialServiceAdapter: UnofficialServiceAdapter by lazy { UnofficialServiceAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_unofficial_services, container, false)

        view.unofficial_service_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.unofficial_service_recyclerview.adapter = unofficialServiceAdapter

        unofficialServiceViewModel.getUnofficialServices()
        unofficialServiceViewModel.readData().observe(viewLifecycleOwner) {
            unofficialServiceAdapter.setData(it)
        }

        view.search_unofficial_service.isSubmitButtonEnabled = true
        view.search_unofficial_service.setOnQueryTextListener(this)

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

        unofficialServiceViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                unofficialServiceAdapter.setData(it)
            }
        })
    }


}