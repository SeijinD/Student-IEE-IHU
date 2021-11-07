package eu.seijindemon.student_iee_ihu.refactor.ui.find.semesters

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentSimpleCourseBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.BASE_URL_SITE_EL
import eu.seijindemon.student_iee_ihu.util.BASE_URL_SITE_EN
import eu.seijindemon.student_iee_ihu.util.LoadLanguage
import eu.seijindemon.student_iee_ihu.util.view.LoadingDialog

@AndroidEntryPoint
class SimpleCourseFragment : BaseFragment<FragmentSimpleCourseBinding>() {

    override fun getViewBinding(): FragmentSimpleCourseBinding {
        return FragmentSimpleCourseBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loading = LoadingDialog(requireActivity())
        loading.startLoading()

        with(binding) {
            simpleCourseWebview.settings.javaScriptEnabled = true
            simpleCourseWebview.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    simpleCourseWebview.loadUrl("javascript:(function a() { " +
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
                "el" -> simpleCourseWebview.loadUrl(BASE_URL_SITE_EL + SimpleCourseFragmentArgs.fromBundle(requireArguments()).courseLink)
                "en" -> simpleCourseWebview.loadUrl(BASE_URL_SITE_EN + SimpleCourseFragmentArgs.fromBundle(requireArguments()).courseLink)
            }
        }

    }

}