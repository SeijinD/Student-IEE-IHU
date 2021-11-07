package eu.seijindemon.student_iee_ihu.refactor.ui.find.rooms

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentEroomsBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment

@AndroidEntryPoint
class ERoomsFragment : BaseFragment<FragmentEroomsBinding>() {

    override fun getViewBinding(): FragmentEroomsBinding {
        return FragmentEroomsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            val eRooms = arrayListOf("94538922872","94395254820","94497383335","93104607777","96738560445","94903290267","95126140664","95600721844")

            e1.setOnClickListener{
                openZoom(eRooms[0])
            }
            e2.setOnClickListener{
                openZoom(eRooms[1])
            }
            e3.setOnClickListener{
                openZoom(eRooms[2])
            }
            e4.setOnClickListener{
                openZoom(eRooms[3])
            }
            e5.setOnClickListener{
                openZoom(eRooms[4])
            }
            e6.setOnClickListener{
                openZoom(eRooms[5])
            }
            e7.setOnClickListener{
                openZoom(eRooms[6])
            }
            e8.setOnClickListener{
                openZoom(eRooms[7])
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