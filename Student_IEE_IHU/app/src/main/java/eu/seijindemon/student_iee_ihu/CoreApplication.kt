package eu.seijindemon.student_iee_ihu

import android.app.Application
import eu.seijindemon.student_iee_ihu.data.local.database.Database
import eu.seijindemon.student_iee_ihu.data.repository.*

class CoreApplication: Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts

    val database by lazy { Database.getDatabase(this) }

    val courseRepository by lazy { CourseRepository(database.courseDao()) }
    val teacherRepository by lazy { TeacherRepository(database.teacherDao()) }
    val mapRepository by lazy { MapRepository(database.mapDao()) }
    val communityRepository by lazy { CommunityRepository(database.communityDao()) }
    val offerRepository by lazy { OfferRepository(database.offerDao()) }
    val officialServiceRepository by lazy { OfficialServiceRepository(database.officialServiceDao()) }
    val unofficialServiceRepository by lazy { UnofficialServiceRepository(database.unofficialServiceDao()) }
    val usefulWebsiteRepository by lazy { UsefulWebsiteRepository(database.usefulWebsiteDao()) }

}