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
import kotlinx.android.synthetic.main.fragment_erooms.view.*

class HRoomsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hrooms, container, false)

        val hRoomsGr = arrayListOf("Α1-Α5", "Β1-Β6", "Γ1-Γ6", "Δ1-Δ4", "Αμφιθέτρο Η")
        val hRoomsEn = arrayListOf("A1-A5", "B1-B6", "C1-C6", "D1-D4", "Amphitheater H")


        return view
    }

}