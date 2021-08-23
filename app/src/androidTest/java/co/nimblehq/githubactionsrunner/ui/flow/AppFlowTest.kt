package co.nimblehq.githubactionsrunner.ui.flow

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.pressBack
import androidx.test.filters.LargeTest
import co.nimblehq.githubactionsrunner.R
import co.nimblehq.githubactionsrunner.domain.repository.ApiRepository
import co.nimblehq.githubactionsrunner.ui.common.waitForApiThen
import co.nimblehq.githubactionsrunner.ui.screens.MainActivity
import co.nimblehq.githubactionsrunner.ui.screens.home.Home
import co.nimblehq.githubactionsrunner.ui.screens.second.Second
import co.nimblehq.githubactionsrunner.ui.screens.webview.WebView
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@LargeTest
@HiltAndroidTest
class AppFlowTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testApiRepository: ApiRepository

    @Before
    fun setup() {
        hiltRule.inject()
        launchActivity()
    }

    @Test
    fun clickOnListItem_navigateToSecondScreen() {
        waitForApiThen {
            Home.scrollToAndClickOnItem(position = 0)

            Second.verifyScreen("title1", "author1")

            pressBack()
            Home.scrollToAndClickOnItem(position = 1)

            Second.verifyScreen("title2", "author2")

            pressBack()
            Home.scrollToAndClickOnItem(position = 2)

            Second.verifyScreen("title3", "author3")

            pressBack()

            Home.verifyScreenAfterLoading()
        }
    }

    @Test
    fun clickOnOpenPostButton_navigateToWebViewScreen() {
        waitForApiThen {
            Home.scrollToAndClickOnItem(position = 0)
            Second.clickOnOpenPostButton()

            WebView.verifyScreen()

            pressBack()

            Second.verifyScreen("title1", "author1")

            pressBack()

            Home.verifyScreenAfterLoading()
        }
    }

    private fun launchActivity(): ActivityScenario<MainActivity>? =
        launch(MainActivity::class.java).apply {
            onActivity { activity ->
                (activity.findViewById(R.id.rvHomeData) as RecyclerView).itemAnimator = null
            }
        }
}
