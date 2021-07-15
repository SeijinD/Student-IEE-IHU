package eu.seijindemon.student_iee_ihu.ui.find.semesters

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.Constants.Companion.BASE_URL_SITE_EN
import eu.seijindemon.student_iee_ihu.utils.Constants.Companion.BASE_URL_SITE_EL
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import eu.seijindemon.student_iee_ihu.utils.LoadingDialog
import kotlinx.android.synthetic.main.fragment_simple_course.view.*

class SimpleCourseFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_simple_course, container, false)

        val loading = LoadingDialog(requireActivity())
        loading.startLoading()

        view.simple_course_webview.settings.javaScriptEnabled = true
        view.simple_course_webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.simple_course_webview?.loadUrl("javascript:(function a() { " +
                        "document.getElementById('masthead').style.display='none';" +
                        "document.getElementById('colophon').style.display='none';" +
                        "document.getElementById('secondary').style.display='none';" +
                        "})()")
                loading.isDismiss()
            }
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }

        when(LoadLanguage.loadLanguage()) {
            "el" -> view.simple_course_webview.loadUrl(BASE_URL_SITE_EL + SimpleCourseFragmentArgs.fromBundle(requireArguments()).courseLink)
            "en" -> view.simple_course_webview.loadUrl(BASE_URL_SITE_EN + SimpleCourseFragmentArgs.fromBundle(requireArguments()).courseLink)
        }

        //Toast.makeText(requireContext(), view.simple_course_webview.url, Toast.LENGTH_LONG).show()

        return view
    }

}