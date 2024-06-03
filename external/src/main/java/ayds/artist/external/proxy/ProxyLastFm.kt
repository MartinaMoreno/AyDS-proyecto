package ayds.artist.external.proxy

import ayds.artist.external.lastfm.LastFmBiography
import ayds.artist.external.lastfm.LastFmService
import ayds.artist.external.lastfm.LastFmServiceImpl
import ayds.songinfo.moredetails.domain.Card
import ayds.songinfo.moredetails.domain.CardSource

class ProxyLastFm(private val lastFmService: LastFmService): ProxyInterface{

    override fun getData(artistName: String): Card {
        val dataLastFmInterface = lastFmService.getArticle(artistName)
        return mapLastFmToCard(dataLastFmInterface)
    }

    private fun mapLastFmToCard(dataLastFm: LastFmBiography): Card {
        return Card(
            artistName = dataLastFm.artistName,
            text = dataLastFm.biography,
            url = dataLastFm.articleUrl,
            source = CardSource.LAST_FM,
            isLocallyStored = true
        )
    }
}