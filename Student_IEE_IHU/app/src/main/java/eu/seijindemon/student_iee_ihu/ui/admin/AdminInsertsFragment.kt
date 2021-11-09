package eu.seijindemon.student_iee_ihu.ui.admin

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentAdminInsertsBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class AdminInsertsFragment : BaseFragment<FragmentAdminInsertsBinding>() {

    override fun getViewBinding(): FragmentAdminInsertsBinding {
        return FragmentAdminInsertsBinding.inflate(layoutInflater)
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