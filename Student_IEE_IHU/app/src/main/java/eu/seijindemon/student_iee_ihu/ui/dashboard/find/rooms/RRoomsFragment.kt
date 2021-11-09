package eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentRroomsBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class RRoomsFragment : BaseFragment<FragmentRroomsBinding>() {

    override fun getViewBinding(): FragmentRroomsBinding {
        return FragmentRroomsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            val rRooms = arrayListOf("93926093065","96592600632","95848116420","93665236660","96848094047","98314633445","91223279873","91280597010")

            r1.setOnClickListener{
                openZoom(rRooms[0])
            }
            r2.setOnClickListener{
                openZoom(rRooms[1])
            }
            r3.setOnClickListener{
                openZoom(rRooms[2])
            }
            r4.setOnClickListener{
                openZoom(rRooms[3])
            }
            r5.setOnClickListener{
                openZoom(rRooms[4])
            }
            r6.setOnClickListener{
                openZoom(rRooms[5])
            }
            r7.setOnClickListener{
                openZoom(rRooms[6])
            }
            r8.setOnClickListener{
                openZoom(rRooms[7])
            }
        }
    }

    private fun openZoom(link: String){
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("zoomus://zoom.us/join?confno=$link")))
        }
        catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=us.zoom.videomeetings")))
        }
    }

}