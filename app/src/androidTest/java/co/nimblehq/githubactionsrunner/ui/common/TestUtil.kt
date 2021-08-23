package co.nimblehq.githubactionsrunner.ui.common

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.core.util.Preconditions
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import co.nimblehq.githubactionsrunner.EmptyHiltActivity
import co.nimblehq.githubactionsrunner.R
import co.nimblehq.githubactionsrunner.repository.TEST_API_DELAY
import co.nimblehq.githubactionsrunner.ui.base.BaseFragment

/**
 * https://github.com/android/architecture-samples/blob/dev-hilt/app/src/androidTest/java/com/example/android/architecture/blueprints/todoapp/HiltExt.kt
 */
inline fun <reified F : BaseFragment<*>> launchFragment(
    fragmentArgs: Bundle? = null
) {
    val startActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            EmptyHiltActivity::class.java
        )
    ).putExtra(THEME_EXTRAS_BUNDLE_KEY, R.style.AppTheme)

    ActivityScenario.launch<EmptyHiltActivity>(startActivityIntent).onActivity { activity ->
        activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(F::class.java.classLoader),
            F::class.java.name
        ).run {
            arguments = fragmentArgs

            activity.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, this, "")
                .commitNow()
        }
    }
}

inline fun waitForApiThen(action: () -> Unit) {
    Thread.sleep(TEST_API_DELAY)
    action.invoke()
}

const val THEME_EXTRAS_BUNDLE_KEY =
    "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY"
