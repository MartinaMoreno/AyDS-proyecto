package ayds.artist.external.proxy

import ayds.songinfo.moredetails.domain.Card

interface ProxyInterface {
    fun getData(artistName: String): Card?
}