package eu.seijindemon.student_iee_ihu.di.module.usefulwebsite

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.data.usefulwebsite.UsefulWebsiteDataSource
import eu.seijindemon.student_iee_ihu.data.usefulwebsite.UsefulWebsiteRepositoryImpl
import eu.seijindemon.student_iee_ihu.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.framework.usefulwebsite.UsefulWebsiteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface UsefulWebisteBindsModule {

    @Binds
    fun bindUsefulWebsiteDataSource(dataSource: UsefulWebsiteDataSourceImpl): UsefulWebsiteDataSource

    @Binds
    fun bindUsefulWebsiteRepository(repository: UsefulWebsiteRepositoryImpl): UsefulWebsiteRepository

}