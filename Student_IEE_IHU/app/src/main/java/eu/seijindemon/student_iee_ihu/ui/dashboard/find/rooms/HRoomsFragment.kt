package eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms

import android.os.Bundle
import android.view.View
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentHroomsBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class HRoomsFragment : BaseFragment<FragmentHroomsBinding>() {

    override fun getViewBinding(): FragmentHroomsBinding {
        return FragmentHroomsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            a1A5.setOnClickListener {
                openPhysicalRoom(getString(R.string.a1_a5), getString(R.string.a1_a5_info),getString(
                    R.string.size_10))
            }

            b1B6.setOnClickListener {
                openPhysicalRoom(getString(R.string.b1_b6), getString(R.string.b1_b6_info),getString(
                    R.string.size_60))
            }

            c1C6.setOnClickListener {
                openPhysicalRoom(getString(R.string.c1_c6), getString(R.string.c1_c6_info),getString(
                    R.string.size_10))
            }

            d1D4.setOnClickListener {
                openPhysicalRoom(getString(R.string.d1_d4), getString(R.string.d1_d4_info),getString(
                    R.string.size_10))
            }

            amphitheaterH.setOnClickListener {
                openPhysicalRoom(getString(R.string.amphitheater_h), getString(R.string.amphitheater_h_info),getString(
                    R.string.size_100))
            }
        }
    }

    private fun openPhysicalRoom(title: String, info: String, size: String) {
        MaterialStyledDialog.Builder(requireContext())
            .setTitle(title)
            .setDescription("-$info\n-$size")
            .setStyle(Style.HEADER_WITH_TITLE)
            .show()
    }

}