package co.nimblehq.githubactionsrunner.di.modules

import android.content.Context
import co.nimblehq.githubactionsrunner.GitHubActionsRunnerApplication
import co.nimblehq.githubactionsrunner.domain.schedulers.BaseSchedulerProvider
import co.nimblehq.githubactionsrunner.domain.schedulers.SchedulerProvider
import co.nimblehq.githubactionsrunner.ui.common.Toaster
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideContext(application: GitHubActionsRunnerApplication): Context = application

    @Provides
    fun toaster(@ApplicationContext context: Context): Toaster = Toaster(context)

    @Provides
    fun schedulerProvider(): BaseSchedulerProvider = SchedulerProvider()
}
