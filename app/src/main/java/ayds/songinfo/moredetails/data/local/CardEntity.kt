package ayds.songinfo.moredetails.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey
    val artistName: String,
    val content: String,
    val url: String,
    val source: Int
)