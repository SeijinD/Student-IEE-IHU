package eu.seijindemon.student_iee_ihu.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.seijindemon.student_iee_ihu.data.local.dao.CommunityDao
import eu.seijindemon.student_iee_ihu.data.local.dao.CourseDao
import eu.seijindemon.student_iee_ihu.data.local.dao.MapDao
import eu.seijindemon.student_iee_ihu.data.local.dao.TeacherDao
import eu.seijindemon.student_iee_ihu.data.model.Community
import eu.seijindemon.student_iee_ihu.data.model.Course
import eu.seijindemon.student_iee_ihu.data.model.Map
import eu.seijindemon.student_iee_ihu.data.model.Teacher

@Database(entities = [Course::class, Teacher::class, Map::class, Community::class],version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun courseDao(): CourseDao

    abstract fun teacherDao(): TeacherDao

    abstract fun mapDao(): MapDao

    abstract fun communityDao(): CommunityDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: eu.seijindemon.student_iee_ihu.data.local.database.Database? = null

        fun getDatabase(context: Context): eu.seijindemon.student_iee_ihu.data.local.database.Database {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    eu.seijindemon.student_iee_ihu.data.local.database.Database::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}