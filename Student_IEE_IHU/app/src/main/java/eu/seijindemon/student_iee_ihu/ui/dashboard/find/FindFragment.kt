package eu.seijindemon.student_iee_ihu.ui.dashboard.find

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentFindBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.Permission
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class FindFragment : BaseFragment<FragmentFindBinding>(), EasyPermissions.PermissionCallbacks {

    override fun getViewBinding(): FragmentFindBinding {
        return FragmentFindBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    fun setupListeners() {
        with(binding) {
            semesters.setOnClickListener {
                findNavController().navigate(R.id.menuSemesters)
            }

            rooms.setOnClickListener {
                findNavController().navigate(R.id.menuRooms)
            }

            courses.setOnClickListener {
                findNavController().navigate(R.id.menuCourses)
            }

            officialServices.setOnClickListener {
                findNavController().navigate(R.id.menuOfficialServices)
            }

            unofficialServices.setOnClickListener {
                findNavController().navigate(R.id.menuUnofficialServices)
            }

            usefulWebsites.setOnClickListener {
                findNavController().navigate(R.id.menuUsefulWebsites)
            }

            offers.setOnClickListener {
                findNavController().navigate(R.id.menuOffers)
            }

            teachers.setOnClickListener {
                findNavController().navigate(R.id.menuTeachers)
            }

            maps.setOnClickListener {
                checkPermissions()
            }
        }
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
            Permission.requestLocationPermission(requireActivity())
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
        if (Permission.hasLocationPermission(requireContext())) {
            findNavController().navigate(R.id.menuMaps)
        }
        else {
            Permission.requestLocationPermission(requireActivity())
        }
    }



}