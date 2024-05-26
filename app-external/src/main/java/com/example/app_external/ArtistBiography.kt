package com.example.app_external

data class ArtistBiography(
    val artistName: String,
    val biography: String,
    val articleUrl: String,
    var isLocallyStored: Boolean = false
)