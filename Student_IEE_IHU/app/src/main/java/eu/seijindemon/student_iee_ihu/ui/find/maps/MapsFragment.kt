package eu.seijindemon.student_iee_ihu.ui.find.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.refactor.framework.map.model.Map
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import kotlinx.android.synthetic.main.fragment_maps.view.*

@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback, SearchView.OnQueryTextListener, GoogleMap.OnMarkerClickListener {

    private lateinit var viewMap: GoogleMap

    private var mapList = emptyList<Map>()

    private val mapViewModel: MapViewModel by activityViewModels()

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

        viewMap.setPadding(0,0,0,200)

        viewMap.uiSettings.isZoomControlsEnabled = true
        viewMap.uiSettings.isMapToolbarEnabled = true
        viewMap.setOnMarkerClickListener(this)

        //viewMap.isMyLocationEnabled

        val zoomLevel = 14.0f
        val latLng = LatLng(40.6571442491575, 22.80383399794818)
        viewMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))


        mapViewModel.readData().observe(this, { list ->
            mapList = list
            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    for (map in mapList) {
                        viewMap.addMarker(
                            MarkerOptions()
                                .position(LatLng((map.latitude)!!.toDouble(), (map.longitude)!!.toDouble()))
                                .title(map.title_gr)
                                .snippet(map.description_gr)
                        )
                    }
                }
                "en" -> {
                    for (map in mapList) {
                        viewMap.addMarker(
                            MarkerOptions()
                                .position(LatLng((map.latitude)!!.toDouble(), (map.longitude)!!.toDouble()))
                                .title(map.title_en)
                                .snippet(map.description_en)
                        )
                    }
                }
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

        if(::viewMap.isInitialized) {
            viewMap.clear()
        }

        mapViewModel.searchDatabase(searchQuery).observe(this, { list ->
            mapList = list
            when(LoadLanguage.loadLanguage()) {
                "el" -> {
                    for (map in mapList) {
                        viewMap.addMarker(
                            MarkerOptions()
                                .position(LatLng((map.latitude)!!.toDouble(), (map.longitude)!!.toDouble()))
                                .title(map.title_gr)
                                .snippet(map.description_gr)
                        )
                    }
                }
                "en" -> {
                    for (map in mapList) {
                        viewMap.addMarker(
                            MarkerOptions()
                                .position(LatLng((map.latitude)!!.toDouble(), (map.longitude)!!.toDouble()))
                                .title(map.title_en)
                                .snippet(map.description_en)
                        )
                    }
                }
            }
        })

        if(::viewMap.isInitialized) {
            val zoomLevel = 14.0f
            val latLng = LatLng(40.6571442491575, 22.80383399794818)
            viewMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        viewMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 16.0f))
        marker.showInfoWindow()
        return true
    }

}
