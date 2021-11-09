package eu.seijindemon.student_iee_ihu.ui.admin

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentAdminBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class AdminFragment : BaseFragment<FragmentAdminBinding>() {

    override fun getViewBinding(): FragmentAdminBinding {
        return FragmentAdminBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            inserts.setOnClickListener{
                findNavController().navigate(R.id.menuAdminInserts)
            }

            updates.setOnClickListener{
                findNavController().navigate(R.id.menuAdminUpdates)
            }

            deletes.setOnClickListener{
                findNavController().navigate(R.id.menuAdminDeletes)
            }
        }
    }

}