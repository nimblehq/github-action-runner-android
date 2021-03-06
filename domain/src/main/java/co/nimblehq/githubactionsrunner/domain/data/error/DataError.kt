package co.nimblehq.githubactionsrunner.domain.data.error

sealed class DataError(
    cause: Throwable?
) : AppError(cause) {

    class GetDataError(cause: Throwable?) : DataError(cause)
}
