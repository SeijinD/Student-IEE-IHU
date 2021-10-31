package eu.seijindemon.student_iee_ihu.ui.find.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_rooms.view.*

@AndroidEntryPoint
class RoomsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rooms, container, false)

        view.zoom_rooms.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuZoomRooms)
        }

        view.rooms.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuPhysicalRooms)
        }

        return view
    }


}