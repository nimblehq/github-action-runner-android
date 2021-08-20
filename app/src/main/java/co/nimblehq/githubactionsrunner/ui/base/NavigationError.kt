package co.nimblehq.githubactionsrunner.ui.base

import co.nimblehq.githubactionsrunner.domain.data.error.AppError

sealed class NavigationError(
    cause: Throwable?
) : AppError(cause) {

    class UnsupportedNavigationError(
        currentGraph: String?,
        currentDestination: String?
    ) : NavigationError(RuntimeException("Unsupported navigation on $currentGraph at $currentDestination"))
}
