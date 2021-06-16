package eu.seijindemon.student_iee_ihu.ui.find.semesters

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.Constants
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import kotlinx.android.synthetic.main.fragment_simple_course.view.*

class SimpleCourseFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_simple_course, container, false)

        val progDailog = ProgressDialog.show(requireContext(), "Loading", "Please wait...", true)
        progDailog.setCancelable(false)

        view.simple_course_webview.settings.javaScriptEnabled = true
        view.simple_course_webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.simple_course_webview?.loadUrl("javascript:(function a() { " +
                        "document.getElementById('masthead').style.display='none';" +
                        "document.getElementById('colophon').style.display='none';" +
                        "document.getElementById('secondary').style.display='none';" +
                        "})()")
                progDailog.dismiss()
            }
        }

        when(LoadLanguage.loadLanguage()) {
            "el" -> view.simple_course_webview.loadUrl(Constants.BASE_URL_SITE_EL + SimpleCourseFragmentArgs.fromBundle(requireArguments()).courseLink)
            "en" -> view.simple_course_webview.loadUrl(Constants.BASE_URL_SITE_EN + SimpleCourseFragmentArgs.fromBundle(requireArguments()).courseLink)
        }

        //Toast.makeText(requireContext(), view.simple_course_webview.url, Toast.LENGTH_LONG).show()

        return view
    }

}