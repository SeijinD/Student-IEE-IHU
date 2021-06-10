package eu.seijindemon.student_iee_ihu.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "useful_website_table")
data class UsefulWebsite(
    var title: String? = null,
    var description: String? = null,
    var category: String? = null,
    var link: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
