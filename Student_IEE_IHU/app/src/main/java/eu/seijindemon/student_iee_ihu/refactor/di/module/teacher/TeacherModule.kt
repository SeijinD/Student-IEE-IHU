package eu.seijindemon.student_iee_ihu.refactor.di.module.teacher

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.seijindemon.student_iee_ihu.refactor.data.teacher.TeacherDataSource
import eu.seijindemon.student_iee_ihu.refactor.data.teacher.TeacherRepositoryImpl
import eu.seijindemon.student_iee_ihu.refactor.domain.teacher.TeacherRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.teacher.TeacherDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface TeacherBindsModule {

    @Binds
    fun bindTeacherDataSource(dataSource: TeacherDataSourceImpl): TeacherDataSource

    @Binds
    fun bindTeacherRepository(repository: TeacherRepositoryImpl): TeacherRepository

}