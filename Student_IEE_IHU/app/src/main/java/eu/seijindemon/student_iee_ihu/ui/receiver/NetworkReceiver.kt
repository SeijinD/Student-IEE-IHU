package eu.seijindemon.student_iee_ihu.ui.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.ui.app.AppActivity
import eu.seijindemon.student_iee_ihu.util.view.LoadingDialog

@AndroidEntryPoint
class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true

        Toast.makeText(context, if (isConnected) "Connection Present" else "No Internet Connection", Toast.LENGTH_SHORT).show()
    }

}