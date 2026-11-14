/**
 * Aurora Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.aurora.music.ui.screens.artist

import com.aurora.music.ui.component.sf
import com.aurora.music.ui.component.SymbolIcon
import androidx.compose.ui.res.painterResource
import com.aurora.music.ui.utils.appTopBarWindowInsets
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import com.aurora.music.ui.ios.compat.Icon
import com.aurora.music.ui.ios.compat.IconButton
import com.aurora.music.ui.ios.compat.AppTheme
import com.aurora.music.ui.ios.compat.Text
import com.aurora.music.ui.ios.compat.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.NavController
import com.composables.sfsymbols.SfSymbols
import com.aurora.music.LocalPlayerAwareWindowInsets
import com.aurora.music.LocalPlayerConnection
import com.aurora.music.R
import com.aurora.music.constants.ArtistSongSortDescendingKey
import com.aurora.music.constants.ArtistSongSortType
import com.aurora.music.constants.ArtistSongSortTypeKey
import com.aurora.music.constants.CONTENT_TYPE_HEADER
import com.aurora.music.constants.HideExplicitKey
import com.aurora.music.extensions.toMediaItem
import com.aurora.music.playback.queues.ListQueue
import com.aurora.music.ui.component.buildAlphabetSectionIndex
import com.aurora.music.ui.component.ListScrollRail
import com.aurora.music.ui.component.HideOnScrollFAB
import com.aurora.music.ui.component.IconButton
import com.aurora.music.ui.component.LocalMenuState
import com.aurora.music.ui.component.SongListItem
import com.aurora.music.ui.component.SortHeader
import com.aurora.music.ui.menu.SongMenu
import com.aurora.music.ui.utils.backToMain
import com.aurora.music.ui.utils.bounceClick
import com.aurora.music.ui.utils.combinedBounceClick
import com.aurora.music.utils.listItemShape
import com.aurora.music.utils.rememberEnumPreference
import com.aurora.music.utils.rememberPreference
import com.aurora.music.viewmodels.ArtistSongsViewModel
import com.aurora.music.ui.component.ScreenLargeTitle
import com.aurora.music.ui.component.ScreenScaffold
import com.aurora.music.ui.component.rememberTitleCollapseProgressState
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ArtistSongsScreen(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: ArtistSongsViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val menuState = LocalMenuState.current
    val haptic = LocalHapticFeedback.current
    val playerConnection = LocalPlayerConnection.current ?: return
    val isPlaying by playerConnection.isEffectivelyPlaying.collectAsStateWithLifecycle()
    val mediaMetadata by playerConnection.mediaMetadata.collectAsStateWithLifecycle()

    val (sortType, onSortTypeChange) = rememberEnumPreference(
        ArtistSongSortTypeKey,
        ArtistSongSortType.CREATE_DATE
    )
    val (sortDescending, onSortDescendingChange) = rememberPreference(
        ArtistSongSortDescendingKey,
        true
    )
    val hideExplicit by rememberPreference(key = HideExplicitKey, defaultValue = false)
    val artist by viewModel.artist.collectAsStateWithLifecycle()
    val songs by viewModel.songs.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val artistSongsTitleProgress = rememberTitleCollapseProgressState(lazyListState, titleItemKey = "title")

    ScreenScaffold(
        title = artist?.artist?.name.orEmpty(),
        titleAlpha = 0f,
        titleAlphaProvider = { artistSongsTitleProgress.value },
        onBack = navController::navigateUp,
        onBackLongClick = navController::backToMain,
    ) { chrome ->
        LazyColumn(
            state = lazyListState,
            modifier = chrome.contentModifier(),
            contentPadding = LocalPlayerAwareWindowInsets.current.asPaddingValues(),
        ) {
            item(key = "title") {
                ScreenLargeTitle(artist?.artist?.name.orEmpty())
            }

            item(
                key = "header",
                contentType = CONTENT_TYPE_HEADER,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    SortHeader(
                        sortType = sortType,
                        sortDescending = sortDescending,
                        onSortTypeChange = onSortTypeChange,
                        onSortDescendingChange = onSortDescendingChange,
                        sortTypeText = { sortType ->
                            when (sortType) {
                                ArtistSongSortType.CREATE_DATE -> R.string.sort_by_create_date
                                ArtistSongSortType.NAME -> R.string.sort_by_name
                                ArtistSongSortType.PLAY_TIME -> R.string.sort_by_play_time
                            }
                        },
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = pluralStringResource(R.plurals.n_song, songs.size, songs.size),
                        style = AppTheme.typography.titleSmall,
                        color = AppTheme.colorScheme.secondary,
                    )
                }
            }

            itemsIndexed(
                items = songs,
                key = { _, item -> item.id },
            ) { index, song ->
                SongListItem(
                    song = song,
                    showInLibraryIcon = true,
                    isActive = song.id == mediaMetadata?.id,
                    isPlaying = isPlaying,
                    shape = listItemShape(index, songs.size),
                    trailingContent = {
                        IconButton(
                            onClick = {
                                menuState.show {
                                    SongMenu(
                                        originalSong = song,
                                        navController = navController,
                                        onDismiss = menuState::dismiss,
                                    )
                                }
                            },
                        ) {
                            SymbolIcon(symbol = sf("ellipsis"), contentDescription = null)
                        }
                    },
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .combinedBounceClick(
                            onClick = {
                                if (song.id == mediaMetadata?.id) {
                                    playerConnection.togglePlayPause()
                                } else {
                                    playerConnection.playQueue(
                                        ListQueue(
                                            title = context.getString(R.string.queue_all_songs),
                                            items = songs.map { it.toMediaItem() },
                                            startIndex = index,
                                        ),
                                    )
                                }
                            },
                            onLongClick = {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                menuState.show {
                                    SongMenu(
                                        originalSong = song,
                                        navController = navController,
                                        onDismiss = menuState::dismiss,
                                    )
                                }
                            },
                        )
                        .animateItem(),
                )
            }
        }

        ListScrollRail(
            lazyListState = lazyListState,
            itemCount = songs.size,
            sectionIndexMap = if (sortType == ArtistSongSortType.NAME) {
                remember(songs) { buildAlphabetSectionIndex(songs) { it.title } }
            } else {
                null
            },
        )

        HideOnScrollFAB(
            lazyListState = lazyListState,
            icon = R.drawable.shuffle,
            onClick = {
                playerConnection.playQueue(
                    ListQueue(
                        title = artist?.artist?.name,
                        items = songs.shuffled().map { it.toMediaItem() },
                    ),
                )
            },
        )
    }
}
