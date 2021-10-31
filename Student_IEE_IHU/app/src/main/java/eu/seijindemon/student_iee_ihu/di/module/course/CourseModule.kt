package eu.seijindemon.student_iee_ihu.di.module.course

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.data.course.CourseDataSource
import eu.seijindemon.student_iee_ihu.data.course.CourseRepositoryImpl
import eu.seijindemon.student_iee_ihu.domain.course.CourseRepository
import eu.seijindemon.student_iee_ihu.framework.course.CourseDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface CourseBindsModule {

    @Binds
    fun bindCourseDataSource(dataSource: CourseDataSourceImpl): CourseDataSource

    @Binds
    fun bindCourseRepository(repository: CourseRepositoryImpl): CourseRepository

}