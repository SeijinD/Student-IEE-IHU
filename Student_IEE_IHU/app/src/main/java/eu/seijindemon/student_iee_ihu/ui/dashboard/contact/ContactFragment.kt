package eu.seijindemon.student_iee_ihu.ui.dashboard.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentContactBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.Permission
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>(), EasyPermissions.PermissionCallbacks {

    override fun getViewBinding(): FragmentContactBinding {
        return FragmentContactBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            firstContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013621")))
            }
            secondContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013604")))
            }
            thirdContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013123")))
            }
            fourthContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013709")))
            }
            fourthContactNumber2Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013712")))
            }
            fifthContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013790")))
            }
            sixthContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013667")))
            }
            sixthContactNumber2Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013124")))
            }
            seventhContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013148")))
            }
            seventhContactNumber2Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013139")))
            }
            eighthContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2152157850")))
            }
            ninthContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013620")))
            }
            tenthContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013592")))
            }
            eleventhContactNumber1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2310013665")))
            }

            firstContactEmail1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "info@iee.ihu.gr", null)))
            }
            secondContactEmail1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "noc@teithe.gr", null)))
            }
            thirdContactEmail1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "dobros@admin.teithe.gr", null)))
            }
            fourthContactEmail1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "erasmus.admin@the.ihu.gr", null)))
            }
            fifthContactEmail1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "praktiki.espa@teithe.gr", null)))
            }
            sixthContactEmail1Button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "merimna@the.ihu.gr", null)))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkPermissions()
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
            Permission.requestCallPermission(requireActivity())
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

    private fun checkPermissions() {
        with(binding) {
            if (Permission.hasCallPermission(requireContext())) {
                contactLayout.isVisible = true
            }
            else {
                contactLayout.isVisible = false
                Permission.requestCallPermission(requireActivity())
            }
        }
    }

}