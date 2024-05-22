package ayds.songinfo.moredetails.data.local

import ayds.songinfo.moredetails.domain.ArtistBiography

interface OtherInfoLocalStorage{
    fun getArticle(artistName: String): ArtistBiography?
    fun insertArtist(artistBiography: ArtistBiography)
}

internal class OtherInfoLocalStorageImpl(private val articleDB: ArticleDatabase): OtherInfoLocalStorage{
    override fun getArticle(artistName: String): ArtistBiography? {
        val artistEntity = articleDB.ArticleDao().getArticleByArtistName(artistName)
        return artistEntity?.let {
            ArtistBiography(artistName, artistEntity.biography, artistEntity.articleUrl)
        }
    }

    override fun insertArtist(artistBiography: ArtistBiography) {
        articleDB.ArticleDao().insertArticle(
            ArticleEntity(
                artistBiography.artistName, artistBiography.biography, artistBiography.articleUrl)
        )
    }
}