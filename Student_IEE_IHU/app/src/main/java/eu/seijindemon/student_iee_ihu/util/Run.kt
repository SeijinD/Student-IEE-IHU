package eu.seijindemon.student_iee_ihu.util

import android.os.Handler
import android.os.Looper

class Run {
    companion object {
        fun after(delay: Long, process: () -> Unit) {
            Handler(Looper.getMainLooper()).postDelayed({
                process()
            }, delay)
        }
    }
}