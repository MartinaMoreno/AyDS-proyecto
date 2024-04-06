package ayds.songinfo.home.model.entities

sealed class Song {
    data class SpotifySong(
        val id: String,
        val songName: String,
        val artistName: String,
        val albumName: String,
        val releaseDate: String,
        val spotifyUrl: String,
        val imageUrl: String,
        var isLocallyStored: Boolean = false
    ) : Song() {

        val release_date_precision: String =
            when(releaseDate.split("-").size) {
                1 -> "year"
                2 -> "month"
                else -> "day"
            }
    }

    object EmptySong : Song()
}

