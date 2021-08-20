package eu.seijindemon.student_iee_ihu.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unofficial_service_table")
data class UnofficialService(
    var title_gr: String? = null,
    var title_en: String? = null,
    var description_gr: String? = null,
    var description_en: String? = null,
    var creator_gr: String? = null,
    var creator_en: String? = null,
    var category: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
