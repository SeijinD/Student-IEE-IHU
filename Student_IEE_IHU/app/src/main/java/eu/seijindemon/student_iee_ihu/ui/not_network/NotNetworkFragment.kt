package eu.seijindemon.student_iee_ihu.ui.not_network

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.WorkerThread
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentNotNetworkBinding
import eu.seijindemon.student_iee_ihu.ui.app.AppActivity
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.NetworkStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotNetworkFragment : BaseFragment<FragmentNotNetworkBinding>() {

    override fun getViewBinding(): FragmentNotNetworkBinding {
        return FragmentNotNetworkBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            refreshNetwork.setOnClickListener {
                if (NetworkStatus.networkAvailable(requireActivity().application)) {
                    startActivity(Intent(requireContext(), AppActivity::class.java))
                    activity?.finish()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            networkAvailable()
        }

    }

    @WorkerThread
    private suspend fun networkAvailable() {
        if (NetworkStatus.networkAvailable(requireActivity().application)) {
            startActivity(Intent(requireContext(), AppActivity::class.java))
            activity?.finish()
        }
    }
}