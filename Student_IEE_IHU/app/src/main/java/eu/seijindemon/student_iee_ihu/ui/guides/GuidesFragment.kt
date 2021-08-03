package eu.seijindemon.student_iee_ihu.ui.guides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_guides.view.*


class GuidesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_guides, container, false)

        view.first_day.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuFirstDay)
        }

        view.zoom.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuZoom)
        }

        view.thesis.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuThesis)
        }

        view.feeding.setOnClickListener{

        }

        view.housing.setOnClickListener{

        }

        view.library.setOnClickListener{

        }

        view.softwares.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuSoftwares)
        }

        return view
    }


}