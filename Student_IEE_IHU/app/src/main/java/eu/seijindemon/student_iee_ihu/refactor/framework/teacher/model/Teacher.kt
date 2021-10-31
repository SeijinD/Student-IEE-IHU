package eu.seijindemon.student_iee_ihu.refactor.framework.teacher.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher_table")
data class Teacher(
    var name_gr: String? = null,
    var name_en: String? = null,
    var email: String? = null,
    var personal_site: String? = null,
    var category_gr: String? = null,
    var category_en: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
