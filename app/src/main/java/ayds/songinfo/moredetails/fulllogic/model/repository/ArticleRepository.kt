package ayds.songinfo.moredetails.fulllogic.model.repository

import ayds.songinfo.home.model.repository.local.spotify.SpotifyLocalStorage
import ayds.songinfo.moredetails.fulllogic.ArticleEntity

interface ArticleRepository{
    fun getArticleByArtistName(artistName: String): ArticleEntity
}

internal class ArticleRepositoryImpl(
    private val articleLocalStorage: SpotifyLocalStorage
): ArticleRepository{

    override fun getArticleByArtistName(artistName: String): ArticleEntity {
        TODO("Not yet implemented")
    }


}