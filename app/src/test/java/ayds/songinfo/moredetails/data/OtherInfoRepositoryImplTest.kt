package ayds.songinfo.moredetails.data

import ayds.artist.external.lastfm.OtherInfoService
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorage
import com.example.app_external.ArtistBiography
import ayds.songinfo.moredetails.domain.OtherInfoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class OtherInfoRepositoryImplTest{
    private val otherInfoLocalStorage: OtherInfoLocalStorage = mockk()
    private val otherInfoService: ayds.artist.external.lastfm.OtherInfoService = mockk()
    private val otherInfoRepository: OtherInfoRepository = OtherInfoRepositoryImpl(otherInfoLocalStorage, otherInfoService)

    @Test
    fun `artist local stored then should return it` () {
        val artistBiography =
            com.example.app_external.ArtistBiography("artistName", "biography", "articleUrl", true)
        every { otherInfoLocalStorage.getArticle("artistName") } returns artistBiography

        val result = otherInfoRepository.getArtistBiography("artistName")

        Assert.assertEquals(artistBiography,result)
        Assert.assertTrue(result.isLocallyStored)
    }

    @Test
    fun `artist not stored then should return article service with no empty biography` () {
        val artistBiography =
            com.example.app_external.ArtistBiography("artistName", "biography", "articleUrl", false)
        every { otherInfoLocalStorage.getArticle("artistName") } returns null
        every { otherInfoService.getArticle("artistName") } returns artistBiography
        every { otherInfoLocalStorage.insertArtist(artistBiography) } returns Unit

        val result = otherInfoRepository.getArtistBiography("artistName")

        Assert.assertEquals(artistBiography,result)
        Assert.assertFalse(result.isLocallyStored)
        verify { otherInfoLocalStorage.insertArtist(artistBiography) }
    }

    @Test
    fun `artist not stored then should return article service with empty biography` () {
        val artistBiography =
            com.example.app_external.ArtistBiography("artistName", "", "articleUrl", false)
        every { otherInfoLocalStorage.getArticle("artistName") } returns null
        every { otherInfoService.getArticle("artistName") } returns artistBiography

        val result = otherInfoRepository.getArtistBiography("artistName")

        Assert.assertEquals(artistBiography,result)
        Assert.assertFalse(result.isLocallyStored)
        verify(inverse = true) { otherInfoLocalStorage.insertArtist(artistBiography) }
    }
}
