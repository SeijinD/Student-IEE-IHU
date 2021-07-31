package eu.seijindemon.student_iee_ihu.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher_table")
data class Teacher(
    var name: String? = null,
    var email: String? = null,
    var personal_site: String? = null,
    var category: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
