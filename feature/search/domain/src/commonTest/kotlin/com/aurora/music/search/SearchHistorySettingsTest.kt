package com.aurora.music.search

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SearchHistorySettingsTest {
    @Test
    fun recordsNonemptyQueriesWhenHistoryIsEnabled() {
        assertTrue(shouldRecordSearchQuery("aurora", SearchHistorySettings()))
    }

    @Test
    fun doesNotRecordQueriesWhenHistoryIsPaused() {
        assertFalse(shouldRecordSearchQuery("aurora", SearchHistorySettings(paused = true)))
    }

    @Test
    fun doesNotRecordQueriesBeforeSettingsAreLoaded() {
        assertFalse(shouldRecordSearchQuery("aurora", null))
    }

    @Test
    fun doesNotRecordEmptyQueries() {
        assertFalse(shouldRecordSearchQuery("", SearchHistorySettings()))
    }
}
