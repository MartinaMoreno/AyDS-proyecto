package ayds.songinfo.home.view

import ayds.songinfo.home.model.entities.Song
import java.text.SimpleDateFormat
import java.util.Locale

interface ReleaseDateResolverFactory {
    fun getReleaseDateResolver(song: Song.SpotifySong): ReleaseDateResolver
}

class ReleaseDateResolverImpl: ReleaseDateResolverFactory {
    override fun getReleaseDateResolver(song: Song.SpotifySong): ReleaseDateResolver =
        when (song.releaseDatePrecision) {
            "day" -> ChangeFormatDay(song)
            "month" -> ChangeFormatMonth(song)
            "year" -> ChangeFormatYear(song)
            else -> ChangeFormatDefault(song)
        }
}

interface ReleaseDateResolver {
    val song: Song.SpotifySong
    fun getReleaseDate(): String
}

internal class ChangeFormatDay(override val song: Song.SpotifySong): ReleaseDateResolver {
    override fun getReleaseDate(): String{
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateDay = originalFormat.parse(song.releaseDate)
        val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return newFormat.format(dateDay)
    }
}

internal class ChangeFormatMonth(override val song: Song.SpotifySong): ReleaseDateResolver {
    override fun getReleaseDate(): String{
        val originalFormat = SimpleDateFormat("yyyy-MM", Locale.getDefault())
        val dateDay = originalFormat.parse(song.releaseDate)
        val newFormat = SimpleDateFormat("MMMM, yyyy", Locale.getDefault())
        return newFormat.format(dateDay)
    }
}

internal class ChangeFormatYear(override val song: Song.SpotifySong): ReleaseDateResolver {
    override fun getReleaseDate(): String{
        return if (isALeap(song.releaseDate.split("-").first().toInt()))
            "${song.releaseDate.split("-").first()} (a leap year)"
        else
            "${song.releaseDate.split("-").first()} (not a leap year)"
    }

    private fun isALeap(n: Int) = (n % 4 == 0) && (n % 100 != 0 || n % 400 == 0)
}

internal class ChangeFormatDefault(override val song: Song.SpotifySong): ReleaseDateResolver {
    override fun getReleaseDate(): String = song.releaseDate
}

