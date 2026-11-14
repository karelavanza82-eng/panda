package com.aurora.music.lyrics

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.aurora.music.constants.EnableBetterLyricsKey
import com.aurora.music.constants.EnableKugouKey
import com.aurora.music.constants.EnableLrcLibKey
import com.aurora.music.constants.EnableMusixmatchKey
import com.aurora.music.constants.EnablePaxsenixKey
import com.aurora.music.constants.EnableSimpMusicKey
import com.aurora.music.constants.EnableUnisonKey
import com.aurora.music.constants.EnableYouLyPlusKey
import com.aurora.music.constants.PreferredLyricsProvider
import com.aurora.music.lyrics.bitchord.PaxSenix
import com.aurora.music.utils.dataStore
import com.aurora.music.utils.get

object LyricsProviderSettings {
    fun idForLegacyPreference(provider: PreferredLyricsProvider): String = when (provider) {
        PreferredLyricsProvider.LRCLIB -> "LrcLib"
        PreferredLyricsProvider.KUGOU -> "Kugou"
        PreferredLyricsProvider.BETTER_LYRICS -> "BetterLyrics"
        PreferredLyricsProvider.SIMPMUSIC -> "SimpMusic"
        PreferredLyricsProvider.YOULYPLUS -> "YouLyPlus"
        PreferredLyricsProvider.PAXSENIX -> "Paxsenix"
    }

    fun enabledKey(id: String): Preferences.Key<Boolean> = when (id) {
        "BetterLyrics" -> EnableBetterLyricsKey
        "Kugou" -> EnableKugouKey
        "LrcLib" -> EnableLrcLibKey
        "Musixmatch" -> EnableMusixmatchKey
        "Paxsenix" -> EnablePaxsenixKey
        "SimpMusic" -> EnableSimpMusicKey
        "Unison" -> EnableUnisonKey
        "YouLyPlus" -> EnableYouLyPlusKey
        else -> booleanPreferencesKey("enableLyrics$id")
    }

    fun isEnabled(context: Context, provider: LyricsProvider): Boolean =
        context.dataStore[enabledKey(provider.id)] ?: true

    fun initialize(context: Context, provider: LyricsProvider) {
        if (provider !is BitChordLyricsProvider) return
        val credentials = context.applicationContext.getSharedPreferences("lyrics_credentials", Context.MODE_PRIVATE)
        PaxSenix.setApiKey(credentials.getString("paxsenix_api_key", "").orEmpty())
    }
}
