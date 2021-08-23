package co.nimblehq.githubactionsrunner.ui.screens.webview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WebViewBundle(val url: String) : Parcelable
