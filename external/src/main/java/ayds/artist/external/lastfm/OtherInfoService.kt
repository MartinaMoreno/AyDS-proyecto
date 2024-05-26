package ayds.artist.external.lastfm

import com.example.app_external.ArtistBiography
import java.io.IOException

interface OtherInfoService{
    fun getArticle(artistName: String): ArtistBiography
}

class OtherInfoServiceImpl(
    private val lastFMAPI: LastFMAPI,
    private val lastFMToArtistBiographyResolver: LastFMToArtistBiographyResolver
    ): OtherInfoService {
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