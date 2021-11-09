package eu.seijindemon.student_iee_ihu.ui.admin

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentAdminDeletesBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class AdminDeletesFragment : BaseFragment<FragmentAdminDeletesBinding>() {

    override fun getViewBinding(): FragmentAdminDeletesBinding {
        return FragmentAdminDeletesBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {

        }
    }

}