package eu.seijindemon.student_iee_ihu.ui.not_network

import android.content.Intent
import android.os.Bundle
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.ui.auth.LoginActivity
import eu.seijindemon.student_iee_ihu.util.NetworkStatus
import kotlinx.android.synthetic.main.activity_not_network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotNetworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_network)

        refresh_network.setOnClickListener{
            if (NetworkStatus.networkAvailable(application)) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

    }

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            networkAvailable()
        }

    }

    @WorkerThread
    private suspend fun networkAvailable() {
        if (NetworkStatus.networkAvailable(application)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}