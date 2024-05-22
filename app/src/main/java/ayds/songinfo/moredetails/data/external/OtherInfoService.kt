package ayds.songinfo.moredetails.data.external

import ayds.songinfo.moredetails.domain.ArtistBiography
import java.io.IOException

interface OtherInfoService{
    fun getArticle(artistName: String): ArtistBiography
}

internal class OtherInfoServiceImpl(
    private val lastFMAPI: LastFMAPI,
    private val lastFMToArtistBiographyResolver: LastFMToArtistBiographyResolver
    ): OtherInfoService{
    override fun getArticle(artistName: String): ArtistBiography {
        var artistBiographyAux = ArtistBiography(artistName,"","")

        try {
            val callResponse = getSongService(artistName)
            artistBiographyAux = lastFMToArtistBiographyResolver.map(callResponse.body(), artistName)

        }catch(e1: IOException){
            e1.printStackTrace()
        }

        return artistBiographyAux
    }

    private fun getSongService(artistName: String) =
        lastFMAPI.getArtistInfo(artistName).execute()

}