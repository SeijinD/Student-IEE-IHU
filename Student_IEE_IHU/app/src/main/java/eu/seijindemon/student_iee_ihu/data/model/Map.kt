package eu.seijindemon.student_iee_ihu.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "map_table")
data class Map(
    var latitude: String? = null,
    var longitude: String? = null,
    var title_gr: String? = null,
    var title_en: String? = null,
    var description_gr: String? = null,
    var description_en: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
