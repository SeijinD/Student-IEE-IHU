package eu.seijindemon.student_iee_ihu.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_webview.view.*


class PDFWebViewFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pdf_webview, container, false)

        view.webview.settings.javaScriptEnabled = true
        view.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {

            }
        }
        val pdf = PDFWebViewFragmentArgs.fromBundle(requireArguments()).url
        view.webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$pdf")

        return view
    }


}