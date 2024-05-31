package ayds.songinfo.moredetails.presentation

import ayds.artist.external.lastfm.LastFmBiography
import ayds.songinfo.moredetails.domain.OtherInfoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class OtherInfoPresenterImplTest{
    private val otherInfoRepository: OtherInfoRepository = mockk()
    private val artistBiographyDescriptionHelper: ArtistBiographyDescriptionHelper = mockk()
    private val otherInfoPresenter: OtherInfoPresenter =
        OtherInfoPresenterImpl(otherInfoRepository, artistBiographyDescriptionHelper)

    @Test
    fun getArtistInfoTest() {
        val artistBiography =
            LastFmBiography("artistName", "biography", "articleUrl")
        every { otherInfoRepository.getArtistBiography("artistName") } returns artistBiography
        every { artistBiographyDescriptionHelper.getDescription(artistBiography) } returns "description"

        val artistBiographyTester: (CardUiState) -> Unit = mockk(relaxed = true)
        otherInfoPresenter.artistBiographyObservable.subscribe(artistBiographyTester)

        otherInfoPresenter.getArtistInfo("artistName")

        val result = ArtistBiographyUiState("artistName", "description", "articleUrl")
        verify { artistBiographyTester(result) }
    }

}