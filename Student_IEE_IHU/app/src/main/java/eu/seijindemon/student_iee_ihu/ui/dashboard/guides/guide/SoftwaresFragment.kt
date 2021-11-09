package eu.seijindemon.student_iee_ihu.ui.dashboard.guides.guide

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentSoftwaresBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class SoftwaresFragment : BaseFragment<FragmentSoftwaresBinding>() {

    override fun getViewBinding(): FragmentSoftwaresBinding {
        return FragmentSoftwaresBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            netbeans.setOnClickListener {
                findNavController().navigate(SoftwaresFragmentDirections.actionMenuSoftwaresToMenuSoftware("netbeans"))
            }
        }
    }

}