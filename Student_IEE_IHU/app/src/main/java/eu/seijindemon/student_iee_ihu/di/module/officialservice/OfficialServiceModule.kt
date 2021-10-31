package eu.seijindemon.student_iee_ihu.di.module.officialservice

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.data.officialservice.OfficialServiceDataSource
import eu.seijindemon.student_iee_ihu.data.officialservice.OfficialServiceRepositoryImpl
import eu.seijindemon.student_iee_ihu.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.framework.officialservice.OfficialServiceDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface OfficialServiceBindsModule {

    @Binds
    fun bindOfficialServiceDataSource(dataSource: OfficialServiceDataSourceImpl): OfficialServiceDataSource

    @Binds
    fun bindOfficialServiceRepository(repository: OfficialServiceRepositoryImpl): OfficialServiceRepository

}