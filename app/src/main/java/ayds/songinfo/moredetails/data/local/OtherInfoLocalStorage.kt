package ayds.songinfo.moredetails.data.local

import com.example.app_external.ArtistBiography

interface OtherInfoLocalStorage{
    fun getArticle(artistName: String): com.example.app_external.ArtistBiography?
    fun insertArtist(artistBiography: com.example.app_external.ArtistBiography)
}

internal class OtherInfoLocalStorageImpl(private val articleDB: ArticleDatabase): OtherInfoLocalStorage{
    override fun getArticle(artistName: String): com.example.app_external.ArtistBiography? {
        val artistEntity = articleDB.ArticleDao().getArticleByArtistName(artistName)
        return artistEntity?.let {
            com.example.app_external.ArtistBiography(
                artistName,
                artistEntity.biography,
                artistEntity.articleUrl
            )
        }
    }

    override fun insertArtist(artistBiography: com.example.app_external.ArtistBiography) {
        articleDB.ArticleDao().insertArticle(
            ArticleEntity(
                artistBiography.artistName, artistBiography.biography, artistBiography.articleUrl)
        )
    }
}