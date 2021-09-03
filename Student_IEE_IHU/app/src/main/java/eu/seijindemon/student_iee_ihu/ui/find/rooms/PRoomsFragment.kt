package eu.seijindemon.student_iee_ihu.ui.find.rooms

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_erooms.view.*
import kotlinx.android.synthetic.main.fragment_prooms.view.*

class PRoomsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_prooms, container, false)

        view.r101.setOnClickListener {
            openPhysicalRoom(getString(R.string.r101), getString(R.string.r101_info),getString(R.string.size_60))
        }

        view.r102.setOnClickListener {
            openPhysicalRoom(getString(R.string.r102), getString(R.string.r102_info),getString(R.string.size_60))
        }

        view.r109.setOnClickListener {
            openPhysicalRoom(getString(R.string.r109), getString(R.string.r109_info),getString(R.string.size_60))
        }

        view.r201.setOnClickListener {
            openPhysicalRoom(getString(R.string.r201), getString(R.string.r201_info),getString(R.string.size_24))
        }

        view.r202.setOnClickListener {
            openPhysicalRoom(getString(R.string.r202), getString(R.string.r202_info),getString(R.string.size_24))
        }

        view.r208.setOnClickListener {
            openPhysicalRoom(getString(R.string.r208), getString(R.string.r208_info),getString(R.string.size_24))
        }

        view.r210.setOnClickListener {
            openPhysicalRoom(getString(R.string.r210), getString(R.string.r210_info),getString(R.string.size_28))
        }

        view.r211.setOnClickListener {
            openPhysicalRoom(getString(R.string.r211), getString(R.string.r211_info),getString(R.string.size_24))
        }

        view.r301.setOnClickListener {
            openPhysicalRoom(getString(R.string.r301), getString(R.string.r301_info),getString(R.string.size_26))
        }

        view.r302.setOnClickListener {
            openPhysicalRoom(getString(R.string.r302), getString(R.string.r302_info),getString(R.string.size_10))
        }

        view.amphitheater_p.setOnClickListener {
            openPhysicalRoom(getString(R.string.amphitheater_p), getString(R.string.amphitheater_p_info),getString(R.string.size_100))
        }

        return view
    }

    private fun openPhysicalRoom(title: String, info: String, size: String) {
        MaterialStyledDialog.Builder(requireContext())
            .setTitle(title)
            .setDescription("-$info\n-$size")
            .setStyle(Style.HEADER_WITH_TITLE)
            .show()
    }

}