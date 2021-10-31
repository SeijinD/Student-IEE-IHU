package eu.seijindemon.student_iee_ihu.di.module.map

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.data.map.MapDataSource
import eu.seijindemon.student_iee_ihu.data.map.MapRepositoryImpl
import eu.seijindemon.student_iee_ihu.domain.map.MapRepository
import eu.seijindemon.student_iee_ihu.framework.map.MapDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface MapBindsModule {

    @Binds
    fun bindMapDataSource(dataSource: MapDataSourceImpl): MapDataSource

    @Binds
    fun bindMapRepository(repository: MapRepositoryImpl): MapRepository

}