package eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentRoomsBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class RoomsFragment : BaseFragment<FragmentRoomsBinding>() {

    override fun getViewBinding(): FragmentRoomsBinding {
        return FragmentRoomsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            zoomRooms.setOnClickListener {
                findNavController().navigate(R.id.menuZoomRooms)
            }
            rooms.setOnClickListener {
                findNavController().navigate(R.id.menuPhysicalRooms)
            }
        }
    }

}