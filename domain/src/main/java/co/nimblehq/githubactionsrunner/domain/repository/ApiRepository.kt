package co.nimblehq.githubactionsrunner.domain.repository

import co.nimblehq.githubactionsrunner.data.service.ApiService
import co.nimblehq.githubactionsrunner.domain.data.Data
import co.nimblehq.githubactionsrunner.domain.data.toDataList
import co.nimblehq.githubactionsrunner.domain.transform
import io.reactivex.Single
import javax.inject.Inject

interface ApiRepository {

    fun exampleData(): Single<List<Data>>
}

class ApiRepositoryImpl @Inject constructor(
    private val service: ApiService
) : ApiRepository {

    override fun exampleData(): Single<List<Data>> {
        return service
            .getExampleData()
            .transform()
            .map { it.toDataList() }
    }
}
