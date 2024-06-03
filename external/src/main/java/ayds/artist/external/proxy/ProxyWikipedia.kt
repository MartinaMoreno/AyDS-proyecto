package ayds.artist.external.proxy

import ayds.artist.external.wikipedia.data.WikipediaArticle
import ayds.artist.external.wikipedia.data.WikipediaTrackService
import ayds.songinfo.moredetails.domain.Card
import ayds.songinfo.moredetails.domain.CardSource

class ProxyWikipedia(private val wikiService: WikipediaTrackService): ProxyInterface{

    override fun getData(artistName: String): Card? {
        val dataWiki = wikiService.getInfo(artistName)
        return dataWiki?.let { mapLastFmToCard(it,artistName) }
    }

    private fun mapLastFmToCard(dataWiki: WikipediaArticle, artistName: String): Card {
        return Card(
            artistName = artistName,
            text = dataWiki.description,
            url = dataWiki.wikipediaURL,
            source = CardSource.WIKIPEDIA,
            isLocallyStored = true
        )
    }
}