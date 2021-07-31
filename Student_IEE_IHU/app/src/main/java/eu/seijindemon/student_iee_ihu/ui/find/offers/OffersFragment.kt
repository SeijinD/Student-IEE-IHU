package eu.seijindemon.student_iee_ihu.ui.find.offers

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
import eu.seijindemon.student_iee_ihu.data.viewmodel.OfferViewModel
import eu.seijindemon.student_iee_ihu.data.viewmodel.OfferViewModelFactory
import kotlinx.android.synthetic.main.fragment_offers.view.*


class OffersFragment : Fragment(), SearchView.OnQueryTextListener {

    private val offerViewModel: OfferViewModel by viewModels { OfferViewModelFactory((activity?.application as CoreApplication).offerRepository) }

    private val offerAdapter: OfferAdapter by lazy { OfferAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_offers, container, false)

        view.offers_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        view.offers_recyclerview.adapter = offerAdapter

        offerViewModel.getOffers()
        offerViewModel.readData().observe(viewLifecycleOwner) {
            offerAdapter.setData(it)
        }

        view.search_offer.isSubmitButtonEnabled = true
        view.search_offer.setOnQueryTextListener(this)

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

        offerViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                offerAdapter.setData(it)
            }
        })
    }


}