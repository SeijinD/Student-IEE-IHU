package eu.seijindemon.student_iee_ihu.util.view

import android.app.Activity
import android.app.AlertDialog
import eu.seijindemon.student_iee_ihu.R

class LoadingDialog (private val mActivity: Activity) {

    private lateinit var isdialog: AlertDialog

    fun startLoading() {
        /**set View*/
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_item,null)
        /**set Dialog*/
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()
    }

    fun isDismiss() {
        isdialog.dismiss()
    }
}