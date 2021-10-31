package eu.seijindemon.student_iee_ihu.ui.guides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_zoom.view.*

@AndroidEntryPoint
class ZoomFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_zoom, container, false)


        view.zoom_link_1.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(ZoomFragmentDirections.actionMenuZoomToPdfWebview("https://www.iee.ihu.gr/wp-content/uploads/2020/03/zoom-manual_setup_sound_video.pdf"))
        }

        view.zoom_link_2.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(ZoomFragmentDirections.actionMenuZoomToPdfWebview("https://www.iee.ihu.gr/wp-content/uploads/2020/03/zoom-manual_plain_participation.pdf"))
        }

        return view
    }


}