package eu.seijindemon.student_iee_ihu.refactor.di.module.community

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.refactor.data.community.CommunityDataSource
import eu.seijindemon.student_iee_ihu.refactor.data.community.CommunityRepositoryImpl
import eu.seijindemon.student_iee_ihu.refactor.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.community.CommunityDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface CommunityBindsModule {

    @Binds
    fun bindCommunityDataSource(dataSource: CommunityDataSourceImpl): CommunityDataSource

    @Binds
    fun bindCommunityRepository(repository: CommunityRepositoryImpl): CommunityRepository

}