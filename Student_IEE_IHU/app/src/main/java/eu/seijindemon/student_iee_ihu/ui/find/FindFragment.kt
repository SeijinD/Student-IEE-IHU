package eu.seijindemon.student_iee_ihu.ui.find

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_find.view.*
import www.sanju.motiontoast.MotionToast

class FindFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    val PERMISSION_LOCATION_REQUEST_CODE = 101

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_find, container, false)

        view.semesters.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuSemesters)
        }

        view.courses.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuCourses)
        }

        view.teachers.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuTeachers)
        }

        view.maps.setOnClickListener {
            checkPermissions(view)
        }

        return view
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireContext()).build().show()
        }
        else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        MotionToast.Companion.createColorToast(
                requireActivity(),
                "Successful",
                "Permissions Granted!",
                MotionToast.Companion.TOAST_SUCCESS,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
    }

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "This application cannot work without Location Permission.",
            PERMISSION_LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun checkPermissions(view: View) {
        if (EasyPermissions.hasPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            Navigation.findNavController(view).navigate(R.id.menuMaps)
        }
        else {
            requestLocationPermission()
        }
    }


}