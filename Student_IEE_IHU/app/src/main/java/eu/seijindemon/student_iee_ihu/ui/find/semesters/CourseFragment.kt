package eu.seijindemon.student_iee_ihu.ui.find.semesters

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.Constants
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import kotlinx.android.synthetic.main.fragment_course.view.*

class CourseFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course, container, false)

        view.course_webview.settings.javaScriptEnabled = true
        view.course_webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.course_webview?.loadUrl("javascript:(function a() { " +
                        "document.getElementById('masthead').style.display='none';" +
                        "document.getElementById('colophon').style.display='none';" +
                        "document.getElementById('secondary').style.display='none';" +
                        "})()")
            }
        }

        when(LoadLanguage.loadLanguage()) {
            "el" -> {
                view.course_webview.loadUrl(Constants.BASE_URL_SITE_EL + CourseFragmentArgs.fromBundle(requireArguments()).courseLink)
            }
            "en" -> {
                view.course_webview.loadUrl(Constants.BASE_URL_SITE_EN + CourseFragmentArgs.fromBundle(requireArguments()).courseLink)
            }
        }

        return view
    }

}