package eu.seijindemon.student_iee_ihu.refactor.ui.find.maps

import android.os.Bundle
import android.view.View
import android.widget.SearchView
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
import eu.seijindemon.student_iee_ihu.databinding.FragmentMapsBinding
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.LoadLanguage

@AndroidEntryPoint
class MapsFragment : BaseFragment<FragmentMapsBinding>(), OnMapReadyCallback, SearchView.OnQueryTextListener, GoogleMap.OnMarkerClickListener {

    override fun getViewBinding(): FragmentMapsBinding {
        return FragmentMapsBinding.inflate(layoutInflater)
    }

    private lateinit var viewMap: GoogleMap

    private var mapList = emptyList<Map>()

    private val viewModel: MapViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupViews()

    }

    private fun setupObservers() {
        with(viewModel) {
            getMaps()
        }
    }

    private fun setupViews() {
        with(binding) {
            val supportMapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment

            searchMap.isSubmitButtonEnabled = true
            searchMap.setOnQueryTextListener(this@MapsFragment)

            supportMapFragment.getMapAsync(this@MapsFragment)
        }
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


        viewModel.readData().observe(this, { list ->
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

        viewModel.searchDatabase(searchQuery).observe(this, { list ->
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
