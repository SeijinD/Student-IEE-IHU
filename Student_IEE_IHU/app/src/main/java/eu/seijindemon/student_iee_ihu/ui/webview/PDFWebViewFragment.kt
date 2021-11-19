package eu.seijindemon.student_iee_ihu.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentPdfWebviewBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class PDFWebViewFragment : BaseFragment<FragmentPdfWebviewBinding>() {

    override fun getViewBinding(): FragmentPdfWebviewBinding {
        return FragmentPdfWebviewBinding.inflate(layoutInflater)
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
                override fun onPageFinished(view: WebView?, url: String?) {}
            }
            val pdf = PDFWebViewFragmentArgs.fromBundle(requireArguments()).url
            webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$pdf")
        }
    }

}