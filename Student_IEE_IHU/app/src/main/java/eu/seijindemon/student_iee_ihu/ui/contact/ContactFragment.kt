package eu.seijindemon.student_iee_ihu.ui.contact

import android.content.Intent
import android.net.Uri
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

        view.first_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013621")))
        }
        view.second_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013604")))
        }
        view.third_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013123")))
        }
        view.fourth_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013709")))
        }
        view.fourth_contact_number_2_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013712")))
        }
        view.fifth_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013790")))
        }
        view.sixth_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013667")))
        }
        view.sixth_contact_number_2_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013124")))
        }
        view.seventh_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013148")))
        }
        view.seventh_contact_number_2_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013139")))
        }
        view.eighth_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2152157850")))
        }
        view.ninth_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013620")))
        }
        view.tenth_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013592")))
        }
        view.eleventh_contact_number_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013665")))
        }

        view.first_contact_email_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "info@iee.ihu.gr", null)))
        }
        view.second_contact_email_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "noc@teithe.gr", null)))
        }
        view.third_contact_email_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "dobros@admin.teithe.gr", null)))
        }
        view.fourth_contact_email_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "erasmus.admin@the.ihu.gr", null)))
        }
        view.fifth_contact_email_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "praktiki.espa@teithe.gr", null)))
        }
        view.sixth_contact_email_1_button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "merimna@the.ihu.gr", null)))
        }
//        view.seventh_contact_email_1_button.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null)))
//        }
//        view.eighth_contact_email_1_button.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null)))
//        }
//        view.ninth_contact_email_1_button.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null)))
//        }
//        view.tenth_contact_email_1_button.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null)))
//        }
//        view.eleventh_contact_email_1_button.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null)))
//        }




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
            getString(R.string.successful),
            getString(R.string.permissions_granted),
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