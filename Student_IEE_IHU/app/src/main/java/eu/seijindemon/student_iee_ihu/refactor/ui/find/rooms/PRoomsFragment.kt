package eu.seijindemon.student_iee_ihu.refactor.ui.find.rooms

import android.os.Bundle
import android.view.View
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentProomsBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment

@AndroidEntryPoint
class PRoomsFragment : BaseFragment<FragmentProomsBinding>() {

    override fun getViewBinding(): FragmentProomsBinding {
        return FragmentProomsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            r101.setOnClickListener {
                openPhysicalRoom(getString(R.string.r101), getString(R.string.r101_info),getString(R.string.size_60))
            }

            r102.setOnClickListener {
                openPhysicalRoom(getString(R.string.r102), getString(R.string.r102_info),getString(R.string.size_60))
            }

            r109.setOnClickListener {
                openPhysicalRoom(getString(R.string.r109), getString(R.string.r109_info),getString(R.string.size_60))
            }

            r201.setOnClickListener {
                openPhysicalRoom(getString(R.string.r201), getString(R.string.r201_info),getString(R.string.size_24))
            }

            r202.setOnClickListener {
                openPhysicalRoom(getString(R.string.r202), getString(R.string.r202_info),getString(R.string.size_24))
            }

            r208.setOnClickListener {
                openPhysicalRoom(getString(R.string.r208), getString(R.string.r208_info),getString(R.string.size_24))
            }

            r210.setOnClickListener {
                openPhysicalRoom(getString(R.string.r210), getString(R.string.r210_info),getString(R.string.size_28))
            }

            r211.setOnClickListener {
                openPhysicalRoom(getString(R.string.r211), getString(R.string.r211_info),getString(R.string.size_24))
            }

            r301.setOnClickListener {
                openPhysicalRoom(getString(R.string.r301), getString(R.string.r301_info),getString(R.string.size_26))
            }

            r302.setOnClickListener {
                openPhysicalRoom(getString(R.string.r302), getString(R.string.r302_info),getString(R.string.size_10))
            }

            amphitheaterP.setOnClickListener {
                openPhysicalRoom(getString(R.string.amphitheater_p), getString(R.string.amphitheater_p_info),getString(
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