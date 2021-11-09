package eu.seijindemon.student_iee_ihu.ui.dashboard.find.offers

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentOffersBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.ui.dashboard.find.offers.adapter.OfferAdapter

@AndroidEntryPoint
class OffersFragment : BaseFragment<FragmentOffersBinding>(), SearchView.OnQueryTextListener {

    override fun getViewBinding(): FragmentOffersBinding {
        return FragmentOffersBinding.inflate(layoutInflater)
    }

    private val viewModel: OfferViewModel by activityViewModels()

    private val offerAdapter: OfferAdapter by lazy { OfferAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupViews()

    }

    private fun setupObservers() {
        with(viewModel) {
            getOffers()
            readData().observe(viewLifecycleOwner) {
                offerAdapter.setData(it)
            }
        }
    }

    private fun setupViews() {
        with(binding) {
            offersRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            offersRecyclerview.adapter = offerAdapter

            searchOffer.isSubmitButtonEnabled = true
            searchOffer.setOnQueryTextListener(this@OffersFragment)
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
                offerAdapter.setData(it)
            }
        })
    }


}