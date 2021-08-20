package eu.seijindemon.student_iee_ihu.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "official_service_table")
data class OfficialService(
    var title_gr: String? = null,
    var title_en: String? = null,
    var description_gr: String? = null,
    var description_en: String? = null,
    var category: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
