package ayds.songinfo.moredetails.data

import ayds.songinfo.moredetails.data.external.OtherInfoService
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorage
import ayds.songinfo.moredetails.domain.ArtistBiography
import ayds.songinfo.moredetails.domain.OtherInfoRepository

class OtherInfoRepositoryImpl(
    private val otherInfoLocalStorage: OtherInfoLocalStorage,
    private val otherInfoService: OtherInfoService): OtherInfoRepository{

        override fun getArtistBiography(artistsName: String): ArtistBiography {
            val articleBD = otherInfoLocalStorage.getArticle(artistsName)
            val biographyFromArtist : ArtistBiography

            if(articleBD != null) {
                biographyFromArtist = articleBD.apply { markItAsLocal() }
            }
            else{
                biographyFromArtist = otherInfoService.getArticle(artistsName)
                if(biographyFromArtist.biography.isNotEmpty()){
                    otherInfoLocalStorage.insertArtist(biographyFromArtist)
                }
            }

            return biographyFromArtist
        }

    private fun ArtistBiography.markItAsLocal() = copy(biography = "[*]$biography")
}
