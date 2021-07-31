package eu.seijindemon.student_iee_ihu.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unofficial_service_table")
data class UnofficialService(
    var title: String? = null,
    var description: String? = null,
    var creator: String? = null,
    var category: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
