package eu.seijindemon.student_iee_ihu.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offer_table")
data class Offer(
    var title: String? = null,
    var description: String? = null,
    var category: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
