package ayds.songinfo.moredetails.domain


interface OtherInfoRepository{
    fun getArtistBiography(artistsName: String): com.example.app_external.ArtistBiography
}