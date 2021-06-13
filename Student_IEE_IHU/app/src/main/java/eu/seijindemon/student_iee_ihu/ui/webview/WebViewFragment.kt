package eu.seijindemon.student_iee_ihu.ui.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_webview.view.*


class WebViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_webview, container, false)

        view.webview.settings.javaScriptEnabled = true
        view.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {

            }
        }
        val url = WebViewFragmentArgs.fromBundle(requireArguments()).url
        view.webview.loadUrl(url)

        return view
    }


}