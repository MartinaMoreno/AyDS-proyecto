package ayds.songinfo.moredetails.fulllogic.model.repository

import ayds.songinfo.moredetails.fulllogic.ArticleEntity

interface ArticleLocalStorage {

    fun insertArticle(query: String, article: ArticleEntity)

    fun getArticleByArtistName(artistName: String): ArticleEntity?
}