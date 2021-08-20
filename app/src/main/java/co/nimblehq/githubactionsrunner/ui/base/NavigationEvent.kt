package co.nimblehq.githubactionsrunner.ui.base

import co.nimblehq.githubactionsrunner.ui.screens.second.SecondBundle
import co.nimblehq.githubactionsrunner.ui.screens.webview.WebViewBundle

sealed class NavigationEvent {
    data class Second(val bundle: SecondBundle) : NavigationEvent()
    data class WebView(val bundle: WebViewBundle) : NavigationEvent()
}
