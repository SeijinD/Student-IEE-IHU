package eu.seijindemon.student_iee_ihu.ui.find

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
import eu.seijindemon.student_iee_ihu.utils.Permissions
import kotlinx.android.synthetic.main.fragment_find.view.*
import www.sanju.motiontoast.MotionToast

class FindFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_find, container, false)

        view.semesters.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuSemesters)
        }

        view.rooms.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuRooms)
        }

        view.courses.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuCourses)
        }

        view.official_services.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuOfficialServices)
        }

        view.unofficial_services.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuUnofficialServices)
        }

        view.useful_websites.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuUsefulWebsites)
        }

        view.offers.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.menuOffers)
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
            Permissions.requestLocationPermission(requireActivity())
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        MotionToast.Companion.createColorToast(
                requireActivity(),
                getString(R.string.successful),
                getString(R.string.permissions_granted),
                MotionToast.Companion.TOAST_SUCCESS,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
    }



    private fun checkPermissions(view: View) {
        if (Permissions.hasLocationPermission(requireContext())) {
            Navigation.findNavController(view).navigate(R.id.menuMaps)
        }
        else {
            Permissions.requestLocationPermission(requireActivity())
        }
    }



}