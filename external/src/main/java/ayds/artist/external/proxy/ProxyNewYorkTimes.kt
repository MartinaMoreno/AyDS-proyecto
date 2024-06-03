package ayds.artist.external.proxy

import ayds.artist.external.newyorktimes.data.NYTimesArticle
import ayds.artist.external.newyorktimes.data.NYTimesService
import ayds.songinfo.moredetails.domain.Card
import ayds.songinfo.moredetails.domain.CardSource

class ProxyNewYorkTimes (private val nyTimeService: NYTimesService): ProxyInterface{

    override fun getData(artistName: String): Card? {
        val dataNYTime = nyTimeService.getArtistInfo(artistName)
        return mapLastFmToCard(dataNYTime)
    }

    private fun mapLastFmToCard(dataNYTime: NYTimesArticle): Card? {
       when(dataNYTime){
           is NYTimesArticle.NYTimesArticleWithData -> {
               return dataNYTime.name?.let {
                   dataNYTime.info?.let { it1 ->
                       Card(
                           artistName = it,
                           text = it1,
                           url = dataNYTime.url,
                           source = CardSource.NY_TIMES,
                           isLocallyStored = true
                       )
                   }
               }
           }

           is NYTimesArticle.EmptyArtistDataExternal -> return null
       }

    }
}