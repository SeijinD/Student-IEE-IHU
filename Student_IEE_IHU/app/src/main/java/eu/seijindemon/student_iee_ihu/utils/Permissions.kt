package eu.seijindemon.student_iee_ihu.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import com.vmadalin.easypermissions.EasyPermissions

class Permissions {
    companion object {

        // Permissions
        private const val PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE = 101
        private const val PERMISSION_BASIC_REQUEST_CODE = 1

        // hasPermission
        fun hasLocationPermission(context: Context): Boolean {
            return EasyPermissions.hasPermissions(context, Manifest.permission.ACCESS_FINE_LOCATION)
        }
        fun hasBasicPermission(context: Context): Boolean {
            return EasyPermissions.hasPermissions(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE
            )
        }

        // requestPermission
        fun requestLocationPermission(activity: Activity) {
            EasyPermissions.requestPermissions(
                activity,
                "This application cannot work without Location Permission.",
                PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
        fun requestBasicPermission(activity: Activity) {
            EasyPermissions.requestPermissions(
                activity,
                "This application cannot work without basic Permissions.",
                PERMISSION_BASIC_REQUEST_CODE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE
            )
        }
    }
}