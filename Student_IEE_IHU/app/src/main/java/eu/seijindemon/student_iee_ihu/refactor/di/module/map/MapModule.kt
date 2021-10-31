package eu.seijindemon.student_iee_ihu.refactor.di.module.map

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.refactor.data.map.MapDataSource
import eu.seijindemon.student_iee_ihu.refactor.data.map.MapRepositoryImpl
import eu.seijindemon.student_iee_ihu.refactor.domain.map.MapRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.map.MapDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface MapBindsModule {

    @Binds
    fun bindMapDataSource(dataSource: MapDataSourceImpl): MapDataSource

    @Binds
    fun bindMapRepository(repository: MapRepositoryImpl): MapRepository

}