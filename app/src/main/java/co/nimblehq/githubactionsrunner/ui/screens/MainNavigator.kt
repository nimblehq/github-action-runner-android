package co.nimblehq.githubactionsrunner.ui.screens

import androidx.fragment.app.Fragment
import co.nimblehq.githubactionsrunner.R
import co.nimblehq.githubactionsrunner.ui.base.BaseNavigator
import co.nimblehq.githubactionsrunner.ui.base.BaseNavigatorImpl
import co.nimblehq.githubactionsrunner.ui.base.NavigationEvent
import co.nimblehq.githubactionsrunner.ui.screens.home.HomeFragmentDirections
import co.nimblehq.githubactionsrunner.ui.screens.second.SecondBundle
import co.nimblehq.githubactionsrunner.ui.screens.second.SecondFragmentDirections
import co.nimblehq.githubactionsrunner.ui.screens.webview.WebViewBundle
import javax.inject.Inject

interface MainNavigator : BaseNavigator

class MainNavigatorImpl @Inject constructor(
    fragment: Fragment
) : BaseNavigatorImpl(fragment), MainNavigator {

    override val navHostFragmentId = R.id.navHostFragment

    override fun navigate(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.Second -> navigateToSecond(event.bundle)
            is NavigationEvent.WebView -> navigateToWebView(event.bundle)
        }
    }

    private fun navigateToSecond(bundle: SecondBundle) {
        val navController = findNavController()
        when (navController?.currentDestination?.id) {
            R.id.homeFragment -> navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToSecondFragment(bundle)
            )
            else -> unsupportedNavigation()
        }
    }

    private fun navigateToWebView(bundle: WebViewBundle) {
        val navController = findNavController()
        when (navController?.currentDestination?.id) {
            R.id.secondFragment -> navController.navigate(
                SecondFragmentDirections.actionSecondFragmentToWebViewFragment(bundle)
            )
            else -> unsupportedNavigation()
        }
    }
}
