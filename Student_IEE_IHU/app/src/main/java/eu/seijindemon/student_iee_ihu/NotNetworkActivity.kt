package eu.seijindemon.student_iee_ihu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.WorkerThread
import androidx.core.content.res.ResourcesCompat
import eu.seijindemon.student_iee_ihu.utils.NetworkStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast

class NotNetworkActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_network)

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