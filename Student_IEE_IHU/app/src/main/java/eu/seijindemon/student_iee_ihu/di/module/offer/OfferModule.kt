package eu.seijindemon.student_iee_ihu.di.module.offer

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.data.offer.OfferDataSource
import eu.seijindemon.student_iee_ihu.data.offer.OfferRepositoryImpl
import eu.seijindemon.student_iee_ihu.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.framework.offer.OfferDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface OfferBindsModule {

    @Binds
    fun bindOfferDataSource(dataSource: OfferDataSourceImpl): OfferDataSource

    @Binds
    fun bindOfferRepository(repository: OfferRepositoryImpl): OfferRepository

}