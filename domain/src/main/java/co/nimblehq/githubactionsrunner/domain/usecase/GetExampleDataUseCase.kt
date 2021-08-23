package co.nimblehq.githubactionsrunner.domain.usecase

import co.nimblehq.githubactionsrunner.domain.data.Data
import co.nimblehq.githubactionsrunner.domain.data.error.DataError.GetDataError
import co.nimblehq.githubactionsrunner.domain.repository.ApiRepository
import co.nimblehq.githubactionsrunner.domain.schedulers.BaseSchedulerProvider
import co.nimblehq.githubactionsrunner.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetExampleDataUseCase @Inject constructor(
    schedulerProvider: BaseSchedulerProvider,
    private val repository: ApiRepository
) : SingleUseCase<Unit, List<Data>>(
    schedulerProvider.io(),
    schedulerProvider.main(),
    ::GetDataError
) {

    override fun create(input: Unit): Single<List<Data>> {
        return repository.exampleData()
    }
}
