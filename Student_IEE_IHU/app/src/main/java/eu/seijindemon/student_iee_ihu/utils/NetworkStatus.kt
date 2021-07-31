package eu.seijindemon.student_iee_ihu.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class NetworkStatus {
    companion object{
        fun networkAvailable(application: Application): Boolean {
            val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnectedOrConnecting ?: false
        }
    }
}