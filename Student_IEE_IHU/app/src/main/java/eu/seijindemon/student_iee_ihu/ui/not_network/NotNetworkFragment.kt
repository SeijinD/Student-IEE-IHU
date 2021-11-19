package eu.seijindemon.student_iee_ihu.ui.not_network

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentNotNetworkBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class NotNetworkFragment : BaseFragment<FragmentNotNetworkBinding>() {

    override fun getViewBinding(): FragmentNotNetworkBinding {
        return FragmentNotNetworkBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}