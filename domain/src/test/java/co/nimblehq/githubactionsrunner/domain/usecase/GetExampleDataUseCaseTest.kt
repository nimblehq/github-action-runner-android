package co.nimblehq.githubactionsrunner.domain.usecase

import co.nimblehq.githubactionsrunner.domain.data.error.DataError
import co.nimblehq.githubactionsrunner.domain.repository.ApiRepository
import co.nimblehq.githubactionsrunner.domain.schedulers.TrampolineSchedulerProvider
import co.nimblehq.githubactionsrunner.domain.test.MockUtil
import io.reactivex.Single
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test

class GetExampleDataUseCaseTest {

    private val mockRepository = mock<ApiRepository>()
    private lateinit var useCase: GetExampleDataUseCase

    @Before
    fun setup() {
        useCase = GetExampleDataUseCase(
            TrampolineSchedulerProvider,
            mockRepository
        )
    }

    @Test
    fun `When execute usecase request successfully, it returns positive result`() {
        val data = MockUtil.dataList
        When calling mockRepository.exampleData() itReturns Single.just(data)

        val testSubscriber = useCase.execute(Unit).test()
        testSubscriber
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(data)
    }

    @Test
    fun `When execute usecase request failed, it returns wrapped error`() {
        When calling mockRepository.exampleData() itReturns Single.error(Throwable())

        val testSubscriber = useCase.execute(Unit).test()
        testSubscriber
            .assertError { it is DataError.GetDataError }
            .assertValueCount(0)
    }
}
