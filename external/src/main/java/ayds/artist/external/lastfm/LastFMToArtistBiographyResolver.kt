package ayds.artist.external.lastfm

import com.example.app_external.ArtistBiography
import com.google.gson.Gson
import com.google.gson.JsonObject

interface LastFMToArtistBiographyResolver{
    fun map(serviceData: String?, artistName: String): ArtistBiography
}

private const val ARTIST = "artist"
private const val BIO = "bio"
private const val CONTENT = "content"
private const val URL = "url"
private const val NO_RESULT = "No Results"

class LasFMToArtistBiographyResolverImpl : LastFMToArtistBiographyResolver {
    override fun map(serviceData: String?, artistName: String): ArtistBiography {
        val gson = Gson()
        val jobj = gson.fromJson(serviceData, JsonObject::class.java)
        val artist = jobj["artist"].getAsJsonObject()
        val bio = artist["bio"].getAsJsonObject()
        val extract = bio["content"]
        val url = artist["url"]
        val text = extract?.asString ?: "No Results"


        return ArtistBiography(artistName, text, url.asString)
    }
}