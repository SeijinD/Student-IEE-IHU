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
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_rrooms.view.*

class RRoomsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rrooms, container, false)

        val rRooms = arrayListOf("93926093065","96592600632","95848116420","93665236660","96848094047","98314633445","91223279873","91280597010")

        view.r1.setOnClickListener{
            openZoom(rRooms[0])
        }
        view.r2.setOnClickListener{
            openZoom(rRooms[1])
        }
        view.r3.setOnClickListener{
            openZoom(rRooms[2])
        }
        view.r4.setOnClickListener{
            openZoom(rRooms[3])
        }
        view.r5.setOnClickListener{
            openZoom(rRooms[4])
        }
        view.r6.setOnClickListener{
            openZoom(rRooms[5])
        }
        view.r7.setOnClickListener{
            openZoom(rRooms[6])
        }
        view.r8.setOnClickListener{
            openZoom(rRooms[7])
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