package eu.seijindemon.student_iee_ihu.ui.find.useful_websites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import eu.seijindemon.student_iee_ihu.CoreApplication
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.viewmodel.UsefulWebsiteViewModel
import eu.seijindemon.student_iee_ihu.data.viewmodel.UsefulWebsiteViewModelFactory
import kotlinx.android.synthetic.main.fragment_useful_websites.view.*


class UsefulWebsitesFragment : Fragment(), SearchView.OnQueryTextListener {

    private val usefulWebsiteViewModel: UsefulWebsiteViewModel by viewModels { UsefulWebsiteViewModelFactory((activity?.application as CoreApplication).usefulWebsiteRepository) }

    private val usefulWebsiteAdapter: UsefulWebsiteAdapter by lazy { UsefulWebsiteAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_useful_websites, container, false)

        view.useful_websites_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.useful_websites_recyclerview.adapter = usefulWebsiteAdapter

        usefulWebsiteViewModel.getUsefulWebsites()
        usefulWebsiteViewModel.readData().observe(viewLifecycleOwner) {
            usefulWebsiteAdapter.setData(it)
        }

        view.search_useful_website.isSubmitButtonEnabled = true
        view.search_useful_website.setOnQueryTextListener(this)

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

        usefulWebsiteViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                usefulWebsiteAdapter.setData(it)
            }
        })
    }


}