package ayds.songinfo.moredetails.presentation

import com.example.app_external.ArtistBiography
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
            com.example.app_external.ArtistBiography("artistName", "biography", "articleUrl")
        every { otherInfoRepository.getArtistBiography("artistName") } returns artistBiography
        every { artistBiographyDescriptionHelper.getDescription(artistBiography) } returns "description"

        val artistBiographyTester: (ArtistBiographyUiState) -> Unit = mockk(relaxed = true)
        otherInfoPresenter.artistBiographyObservable.subscribe(artistBiographyTester)

        otherInfoPresenter.getArtistInfo("artistName")

        val result = ArtistBiographyUiState("artistName", "description", "articleUrl")
        verify { artistBiographyTester(result) }
    }

}