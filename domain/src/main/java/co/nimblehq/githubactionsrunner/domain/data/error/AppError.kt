package co.nimblehq.githubactionsrunner.domain.data.error

import co.nimblehq.githubactionsrunner.data.service.error.JsonApiException

open class AppError(
    override val cause: Throwable?
) : Throwable(cause) {

    val readableMessage: String?
        get() = (cause as? JsonApiException)?.error?.message
}

class Ignored(cause: Throwable?) : AppError(cause)

class NoConnectivityError(cause: Throwable?) : AppError(cause)
