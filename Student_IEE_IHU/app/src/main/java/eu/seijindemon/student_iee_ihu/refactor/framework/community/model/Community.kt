package eu.seijindemon.student_iee_ihu.refactor.framework.community.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "community_table")
data class Community(
    var title_gr: String? = null,
    var title_en: String? = null,
    var category: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
