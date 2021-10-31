package eu.seijindemon.student_iee_ihu.refactor.di.module.usefulwebsite

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.refactor.data.usefulwebsite.UsefulWebsiteDataSource
import eu.seijindemon.student_iee_ihu.refactor.data.usefulwebsite.UsefulWebsiteRepositoryImpl
import eu.seijindemon.student_iee_ihu.refactor.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.usefulwebsite.UsefulWebsiteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface UsefulWebisteBindsModule {

    @Binds
    fun bindUsefulWebsiteDataSource(dataSource: UsefulWebsiteDataSourceImpl): UsefulWebsiteDataSource

    @Binds
    fun bindUsefulWebsiteRepository(repository: UsefulWebsiteRepositoryImpl): UsefulWebsiteRepository

}