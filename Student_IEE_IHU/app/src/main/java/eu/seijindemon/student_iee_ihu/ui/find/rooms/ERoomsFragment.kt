package eu.seijindemon.student_iee_ihu.ui.find.rooms

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_erooms.view.*

@AndroidEntryPoint
class ERoomsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_erooms, container, false)

        val eRooms = arrayListOf("94538922872","94395254820","94497383335","93104607777","96738560445","94903290267","95126140664","95600721844")

        view.e1.setOnClickListener{
            openZoom(eRooms[0])
        }
        view.e2.setOnClickListener{
            openZoom(eRooms[1])
        }
        view.e3.setOnClickListener{
            openZoom(eRooms[2])
        }
        view.e4.setOnClickListener{
            openZoom(eRooms[3])
        }
        view.e5.setOnClickListener{
            openZoom(eRooms[4])
        }
        view.e6.setOnClickListener{
            openZoom(eRooms[5])
        }
        view.e7.setOnClickListener{
            openZoom(eRooms[6])
        }
        view.e8.setOnClickListener{
            openZoom(eRooms[7])
        }

        return view
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