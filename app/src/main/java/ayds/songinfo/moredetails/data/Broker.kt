package ayds.songinfo.moredetails.data

import ayds.artist.external.proxy.ProxyLastFm
import ayds.artist.external.proxy.ProxyNewYorkTimes
import ayds.artist.external.proxy.ProxyWikipedia
import ayds.songinfo.moredetails.domain.Card

class Broker(
    private val proxyLastFm: ProxyLastFm,
    private val proxyNYTimes: ProxyNewYorkTimes,
    private val proxyWikipedia: ProxyWikipedia) {

    fun getCardsFromServices(artistName: String): List<Card?> {
        val dataLastFm = proxyLastFm.getData(artistName)
        val dataNYTimes = proxyNYTimes.getData(artistName)
        val dataWikipedia = proxyWikipedia.getData(artistName)

        return listOf(dataLastFm,dataNYTimes,dataWikipedia)
    }
}