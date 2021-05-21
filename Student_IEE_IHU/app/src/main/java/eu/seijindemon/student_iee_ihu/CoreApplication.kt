package eu.seijindemon.student_iee_ihu

import android.app.Application
import eu.seijindemon.student_iee_ihu.data.local.database.Database
import eu.seijindemon.student_iee_ihu.data.repository.TeacherRepository

class CoreApplication: Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts

    val database by lazy { Database.getDatabase(this) }
    val teacherRepository by lazy { TeacherRepository(database.teacherDao()) }

}