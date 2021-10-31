package eu.seijindemon.student_iee_ihu.refactor.ui.guides.guide

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentZoomBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment

@AndroidEntryPoint
class ZoomFragment : BaseFragment<FragmentZoomBinding>() {

    override fun getViewBinding(): FragmentZoomBinding {
        return FragmentZoomBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            zoomLink1.setOnClickListener{
                findNavController().navigate(ZoomFragmentDirections.actionMenuZoomToPdfWebview("https://www.iee.ihu.gr/wp-content/uploads/2020/03/zoom-manual_setup_sound_video.pdf"))
            }

            zoomLink2.setOnClickListener{
                findNavController().navigate(ZoomFragmentDirections.actionMenuZoomToPdfWebview("https://www.iee.ihu.gr/wp-content/uploads/2020/03/zoom-manual_plain_participation.pdf"))
            }
        }
    }

}