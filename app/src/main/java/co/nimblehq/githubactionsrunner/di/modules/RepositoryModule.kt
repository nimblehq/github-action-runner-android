package co.nimblehq.githubactionsrunner.di.modules

import co.nimblehq.githubactionsrunner.data.service.ApiService
import co.nimblehq.githubactionsrunner.domain.repository.ApiRepository
import co.nimblehq.githubactionsrunner.domain.repository.ApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideApiRepository(
        apiService: ApiService
    ): ApiRepository = ApiRepositoryImpl(apiService)
}
