package ayds.songinfo.moredetails.domain


interface OtherInfoRepository{
    fun getArtistBiography(artistsName: String): ArtistBiography
}