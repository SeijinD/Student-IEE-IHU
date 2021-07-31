package eu.seijindemon.student_iee_ihu.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class Course(
    var title: String? = null,
    var semester: String? = null,
    var teachers: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
