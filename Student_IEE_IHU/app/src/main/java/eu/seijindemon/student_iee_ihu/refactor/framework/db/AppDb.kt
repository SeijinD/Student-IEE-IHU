package eu.seijindemon.student_iee_ihu.refactor.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.seijindemon.student_iee_ihu.refactor.framework.community.model.Community
import eu.seijindemon.student_iee_ihu.refactor.framework.course.model.Course
import eu.seijindemon.student_iee_ihu.refactor.framework.db.dao.*
import eu.seijindemon.student_iee_ihu.refactor.framework.map.model.Map
import eu.seijindemon.student_iee_ihu.refactor.framework.offer.model.Offer
import eu.seijindemon.student_iee_ihu.refactor.framework.officialservice.model.OfficialService
import eu.seijindemon.student_iee_ihu.refactor.framework.teacher.model.Teacher
import eu.seijindemon.student_iee_ihu.refactor.framework.unofficialservice.model.UnofficialService
import eu.seijindemon.student_iee_ihu.refactor.framework.usefulwebsite.model.UsefulWebsite


@Database(entities = [Course::class, Teacher::class, Map::class, Community::class, Offer::class, OfficialService::class, UnofficialService::class, UsefulWebsite::class],version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun courseDao(): CourseDao

    abstract fun teacherDao(): TeacherDao

    abstract fun mapDao(): MapDao

    abstract fun communityDao(): CommunityDao

    abstract fun offerDao(): OfferDao

    abstract fun officialServiceDao(): OfficialServiceDao

    abstract fun unofficialServiceDao(): UnofficialServiceDao

    abstract fun usefulWebsiteDao(): UsefulWebsiteDao

    companion object {

        @Volatile
        private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDb::class.java,
                        "my_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}