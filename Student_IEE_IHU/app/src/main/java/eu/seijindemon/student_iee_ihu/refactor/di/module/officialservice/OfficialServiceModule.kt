package eu.seijindemon.student_iee_ihu.refactor.di.module.officialservice

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.refactor.data.officialservice.OfficialServiceDataSource
import eu.seijindemon.student_iee_ihu.refactor.data.officialservice.OfficialServiceRepositoryImpl
import eu.seijindemon.student_iee_ihu.refactor.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.officialservice.OfficialServiceDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface OfficialServiceBindsModule {

    @Binds
    fun bindOfficialServiceDataSource(dataSource: OfficialServiceDataSourceImpl): OfficialServiceDataSource

    @Binds
    fun bindOfficialServiceRepository(repository: OfficialServiceRepositoryImpl): OfficialServiceRepository

}