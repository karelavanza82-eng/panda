package com.aurora.music.utils

/** Android entry point for the shared artist wordmark lookup. */
object ArtistSignature {
    suspend fun findUrl(artistName: String): String? = com.aurora.music.canvas.ArtistSignature.findUrl(artistName)
}
