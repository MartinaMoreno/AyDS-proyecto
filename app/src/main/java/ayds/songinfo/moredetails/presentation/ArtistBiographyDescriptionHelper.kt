package ayds.songinfo.moredetails.presentation

import com.example.app_external.ArtistBiography
import java.util.Locale

interface ArtistBiographyDescriptionHelper {
    fun getDescription(artistBiography: ArtistBiography): String
}

private const val HEADER = "<html><div width=400><font face=\"arial\">"
private const val FOOTER = "</font></div></html>"
private const val LOCAL_MARKER = "[*]"

internal class ArtistBiographyDescriptionHelperImpl: ArtistBiographyDescriptionHelper {
    override fun getDescription(artistBiography: ArtistBiography): String {
        val text = getTextBiography(artistBiography)
        return textToHtml(text, artistBiography.artistName)
    }

    private fun getTextBiography(artistBiography: ArtistBiography): String {
        val prefix = if (artistBiography.isLocallyStored) LOCAL_MARKER else ""
        val text = artistBiography.biography.replace("\\n","\n")
        return "$prefix$text"
    }

    private fun textToHtml(text: String, term: String): String {
        val builder = StringBuilder()
        builder.append(HEADER)
        val textWithBold = text
            .replace("'", " ")
            .replace("\n", "<br>")
            .replace(
                "(?i)$term".toRegex(),
                "<b>" + term.uppercase(Locale.getDefault()) + "</b>"
            )
        builder.append(textWithBold)
        builder.append(FOOTER)

        return builder.toString()
    }
}