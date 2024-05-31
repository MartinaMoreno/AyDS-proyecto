package ayds.songinfo.moredetails.data

import ayds.artist.external.lastfm.LastFmService
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorage
import ayds.artist.external.lastfm.LastFmBiography
import ayds.songinfo.moredetails.domain.Card
import ayds.songinfo.moredetails.domain.CardSource
import ayds.songinfo.moredetails.domain.OtherInfoRepository

internal class OtherInfoRepositoryImpl(
    private val otherInfoLocalStorage: OtherInfoLocalStorage,
    private val lastFmService: LastFmService
): OtherInfoRepository{

        override fun getCard(artistsName: String): Card {
            val cardBD = otherInfoLocalStorage.getCard(artistsName)
            val card : Card

            if(cardBD != null) {
                card = cardBD.apply { markItAsLocal() }
            }
            else{
                card = lastFmService.getArticle(artistsName).toCard()
                if(card.text.isNotEmpty()){
                    otherInfoLocalStorage.insertCard(card)
                }
            }

            return card
        }

    private fun Card.markItAsLocal() {
        isLocallyStored = true
    }
}

private fun LastFmBiography.toCard() = Card(artistName, biography, articleUrl, CardSource.LAST_FM)
