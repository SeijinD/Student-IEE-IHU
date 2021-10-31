package eu.seijindemon.student_iee_ihu.framework.offer.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offer_table")
data class Offer(
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
