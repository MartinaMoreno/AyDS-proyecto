package ayds.songinfo.moredetails.presentation

import com.example.app_external.ArtistBiography
import org.junit.Assert
import org.junit.Test

class ArtistBiographyDescriptionHelperImplTest{
    private val artistBiographyDescriptionHelper : ArtistBiographyDescriptionHelper = ArtistBiographyDescriptionHelperImpl()

    @Test
    fun `non local biography should return description` () {
        val artistBiography =
            com.example.app_external.ArtistBiography("artistName", "biography", "articleUrl", true)
        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        Assert.assertEquals(
            "<html><div width=400><font face=\"arial\">[*]biography</font></div></html>",
            result
        )
    }

    @Test
    fun `non local biography with double slash fixed should return description`() {
        val artistBiography =
            com.example.app_external.ArtistBiography("artist", "biography", "url", false)

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        Assert.assertEquals(
            "<html><div width=400><font face=\"arial\">biography</font></div></html>",
            result
        )
    }

    @Test
    fun `non local biography with apostrophe removed should return description`() {
        val artistBiography =
            com.example.app_external.ArtistBiography("artist", "biography'n", "url", false)

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        Assert.assertEquals(
            "<html><div width=400><font face=\"arial\">biography n</font></div></html>",
            result
        )
    }

    @Test
    fun `non local biography should return description with bold artist name`() {
        val artistBiography =
            com.example.app_external.ArtistBiography("artist", "biography\\n", "url", false)

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        Assert.assertEquals(
            "<html><div width=400><font face=\"arial\">biography<br></font></div></html>",
            result
        )
    }

    @Test
    fun `should map break lines`() {
        val artistBiography =
            com.example.app_external.ArtistBiography("artist", "biography\n", "url", false)

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        Assert.assertEquals(
            "<html><div width=400><font face=\"arial\">biography<br></font></div></html>",
            result
        )
    }
    @Test
    fun `should set artist name bold`() {
        val artistBiography =
            com.example.app_external.ArtistBiography("artist", "biography artist", "url", false)

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        Assert.assertEquals(
            "<html><div width=400><font face=\"arial\">biography <b>ARTIST</b></font></div></html>",
            result
        )
    }
}

