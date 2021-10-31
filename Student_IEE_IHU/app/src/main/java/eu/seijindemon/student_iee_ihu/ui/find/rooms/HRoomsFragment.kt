package eu.seijindemon.student_iee_ihu.ui.find.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_hrooms.view.*

@AndroidEntryPoint
class HRoomsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hrooms, container, false)

        view.a1_a5.setOnClickListener {
            openPhysicalRoom(getString(R.string.a1_a5), getString(R.string.a1_a5_info),getString(R.string.size_10))
        }

        view.b1_b6.setOnClickListener {
            openPhysicalRoom(getString(R.string.b1_b6), getString(R.string.b1_b6_info),getString(R.string.size_60))
        }

        view.c1_c6.setOnClickListener {
            openPhysicalRoom(getString(R.string.c1_c6), getString(R.string.c1_c6_info),getString(R.string.size_10))
        }

        view.d1_d4.setOnClickListener {
            openPhysicalRoom(getString(R.string.d1_d4), getString(R.string.d1_d4_info),getString(R.string.size_10))
        }

        view.amphitheater_h.setOnClickListener {
            openPhysicalRoom(getString(R.string.amphitheater_h), getString(R.string.amphitheater_h_info),getString(R.string.size_100))
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