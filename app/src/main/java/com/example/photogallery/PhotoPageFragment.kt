package com.example.photogallery

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback

private const val ARG_URI = "photo_page_url"
private const val TAG = "PhotoPageFragment"

class PhotoPageFragment() : VisibleFragment() {

    private lateinit var uri: Uri
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uri = arguments?.getParcelable(ARG_URI) ?: Uri.EMPTY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_page, container, false)

        webView = view.findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(uri.toString())
        val callback = requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            })

        return view
    }


    companion object {
        fun newInstance(uri: Uri): PhotoPageFragment {
            return PhotoPageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_URI, uri)
                }
            }
        }
    }
}