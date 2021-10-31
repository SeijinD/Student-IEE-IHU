package eu.seijindemon.student_iee_ihu.refactor.di.module.unofficilianservice

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.refactor.data.unofficilianservice.UnofficialServiceDataSource
import eu.seijindemon.student_iee_ihu.refactor.data.unofficilianservice.UnofficialServiceRepositoryImpl
import eu.seijindemon.student_iee_ihu.refactor.domain.unofficilianservice.UnofficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.unofficialservice.UnofficialServiceDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface UnofficialServiceBindsModule {

    @Binds
    fun bindUnofficialServiceDataSource(dataSource: UnofficialServiceDataSourceImpl): UnofficialServiceDataSource

    @Binds
    fun bindUnofficialServiceRepository(repository: UnofficialServiceRepositoryImpl): UnofficialServiceRepository

}