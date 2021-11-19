package eu.seijindemon.student_iee_ihu.ui.webview

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentWebviewBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebviewBinding>() {

    override fun getViewBinding(): FragmentWebviewBinding {
        return FragmentWebviewBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupViews() {
        with(binding) {
            webview.settings.javaScriptEnabled = true
            webview.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    webview.loadUrl(url!!)
                }
                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    handler?.proceed()
                }
            }
            val url = WebViewFragmentArgs.fromBundle(requireArguments()).url
            webview.loadUrl(url)
        }
    }

}