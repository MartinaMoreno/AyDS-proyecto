package ayds.songinfo.moredetails.domain

import ayds.artist.external.lastfm.LastFmBiography


interface OtherInfoRepository{
    fun getCard(artistsName: String): Card
}