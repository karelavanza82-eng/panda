package com.aurora.music.history

import com.aurora.music.db.entities.EventWithSong

interface HistoryLocalDataSource {
    suspend fun loadEvents(limit: Int, offset: Int, hideVideoSongs: Boolean, query: String): List<EventWithSong>
}
