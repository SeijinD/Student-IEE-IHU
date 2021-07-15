package eu.seijindemon.student_iee_ihu.ui.find.google_maps

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import eu.seijindemon.student_iee_ihu.CoreApplication
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.local.viewmodel.MapViewModel
import eu.seijindemon.student_iee_ihu.data.local.viewmodel.MapViewModelFactory
import eu.seijindemon.student_iee_ihu.data.model.Map
import kotlinx.android.synthetic.main.fragment_maps.view.*

class MapsFragment : Fragment(), OnMapReadyCallback, SearchView.OnQueryTextListener{

    private lateinit var viewMap: GoogleMap

    private var mapList = emptyList<Map>()

    private val mapViewModel: MapViewModel by viewModels { MapViewModelFactory((activity?.application as CoreApplication).mapRepository) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)

        val supportMapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment

        view.search_map.isSubmitButtonEnabled = true
        view.search_map.setOnQueryTextListener(this)

        mapViewModel.getMaps()

        supportMapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        viewMap = googleMap

        mapViewModel.readData().observe(this, { list ->
            mapList = list
            for (map in mapList) {
                viewMap.addMarker(
                    MarkerOptions()
                        .position(LatLng((map.latitude)!!.toDouble(), (map.longitude)!!.toDouble()))
                        .title(map.name)
                        .snippet(map.description)
                )
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchDatabase(newText)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewMap.clear()

        mapViewModel.searchDatabase(searchQuery).observe(this, { list ->
            mapList = list
            for (map in mapList) {
                viewMap.addMarker(
                    MarkerOptions()
                        .position(LatLng((map.latitude)!!.toDouble(), (map.longitude)!!.toDouble()))
                        .title(map.name)
                        .snippet(map.description)
                )
            }
        })
    }

}