package eu.seijindemon.student_iee_ihu.refactor.di.module.offer

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.refactor.data.offer.OfferDataSource
import eu.seijindemon.student_iee_ihu.refactor.data.offer.OfferRepositoryImpl
import eu.seijindemon.student_iee_ihu.refactor.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.offer.OfferDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface OfferBindsModule {

    @Binds
    fun bindOfferDataSource(dataSource: OfferDataSourceImpl): OfferDataSource

    @Binds
    fun bindOfferRepository(repository: OfferRepositoryImpl): OfferRepository

}