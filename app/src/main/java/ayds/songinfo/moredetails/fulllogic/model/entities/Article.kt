package ayds.songinfo.moredetails.fulllogic.model.entities

sealed class Article{
    data class ArticleArtist(
        val artistName: String,
        val biography: String,
        val articleUrl: String
    ): Article()
}