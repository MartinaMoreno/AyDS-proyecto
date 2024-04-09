package ayds.songinfo.home.view

import ayds.songinfo.home.model.entities.Song.EmptySong
import ayds.songinfo.home.model.entities.Song
import ayds.songinfo.home.model.entities.Song.SpotifySong
import java.text.SimpleDateFormat
import java.util.Locale

interface SongDescriptionHelper {
    fun getSongDescriptionText(song: Song = EmptySong): String
}

internal class SongDescriptionHelperImpl : SongDescriptionHelper {
    override fun getSongDescriptionText(song: Song): String {
        return when (song) {
            is SpotifySong ->
                "${
                    "Song: ${song.songName} " +
                            if (song.isLocallyStored) "[*]" else ""
                }\n" +
                        "Artist: ${song.artistName}\n" +
                        "Album: ${song.albumName}\n" +
                        "Release date: ${song.releaseDate()}"
            else -> "Song not found"
        }
    }

    private fun SpotifySong.releaseDate(): String =
        when(this.releaseDatePrecision) {
            "year" -> changeFormatYear(this.releaseDate)
            "month" -> changeFormatMonth(this.releaseDate)
            "day" -> changeFormatDay(this.releaseDate)
            else -> ""
        }

    private fun changeFormatDay(release: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateDay = originalFormat.parse(release)
        val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return newFormat.format(dateDay)
    }

    private fun changeFormatMonth(release: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM", Locale.getDefault())
        val dateDay = originalFormat.parse(release)
        val newFormat = SimpleDateFormat("MMMM, yyyy", Locale.getDefault())
        return newFormat.format(dateDay)
    }

    private fun changeFormatYear(release: String): String {
        return if (isALeap(release.split("-").first().toInt()))
            "${release.split("-").first()} (a leap year)"
        else
            "${release.split("-").first()} (not a leap year)"
    }

    private fun isALeap(n: Int) = (n % 4 == 0) && (n % 100 != 0 || n % 400 == 0)
}