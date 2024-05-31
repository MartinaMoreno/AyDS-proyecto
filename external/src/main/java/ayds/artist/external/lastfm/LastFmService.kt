package ayds.artist.external.lastfm

import java.io.IOException

interface LastFmService{
    fun getArticle(artistName: String): LastFmBiography
}

internal class LastFmServiceImpl(
    private val lastFMAPI: LastFMAPI,
    private val lastFMToArtistBiographyResolver: LastFMToArtistBiographyResolver
    ): LastFmService {
    override fun getArticle(artistName: String): LastFmBiography {
        var artistBiographyAux = LastFmBiography(artistName,"","")

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