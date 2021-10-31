package eu.seijindemon.student_iee_ihu.ui.guides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_guides.view.*

@AndroidEntryPoint
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
            Navigation.findNavController(view).navigate(R.id.menuFeeding)
        }

        view.housing.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuHousing)
        }

        view.library.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuLibrary)
        }

        view.softwares.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuSoftwares)
        }

        return view
    }


}