package eu.seijindemon.student_iee_ihu.ui.app

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.ActivityAppBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseActivity
import eu.seijindemon.student_iee_ihu.ui.receiver.NetworkReceiver

@AndroidEntryPoint
class AppActivity : BaseActivity<ActivityAppBinding, AppViewModel>(AppViewModel::class.java) {

    override fun getViewBinding(): ActivityAppBinding {
        return ActivityAppBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    private var broadcastReceiver = NetworkReceiver()

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

}