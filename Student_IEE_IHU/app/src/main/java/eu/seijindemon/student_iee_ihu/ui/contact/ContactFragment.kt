package eu.seijindemon.student_iee_ihu.ui.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.Permissions
import kotlinx.android.synthetic.main.fragment_contact.view.*
import www.sanju.motiontoast.MotionToast

class ContactFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)



        return view
    }

    override fun onResume() {
        super.onResume()
        checkPermissions(super.requireView())
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
            Permissions.requestCallPermission(requireActivity())
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

    private fun checkPermissions(view: View) {
        if (Permissions.hasCallPermission(requireContext())) {
            view.contact_layout.isVisible = true
        }
        else {
            view.contact_layout.isVisible = false
            Permissions.requestCallPermission(requireActivity())
        }
    }

}