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

class PRoomsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_prooms, container, false)

        val pRoomsGr = arrayListOf("101", "102", "109", "201", "202", "208", "210", "211", "Αμφιθέατρο Π")
        val pRoomsEn = arrayListOf("101", "102", "109", "201", "202", "208", "210", "211", "Amphitheater P")




        return view
    }

}