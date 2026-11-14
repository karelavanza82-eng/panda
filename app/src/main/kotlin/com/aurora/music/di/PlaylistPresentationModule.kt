package com.aurora.music.di

import com.aurora.music.viewmodels.AppleMusicImportViewModel
import com.aurora.music.viewmodels.AutoPlaylistViewModel
import com.aurora.music.viewmodels.PlaylistsViewModel
import com.aurora.music.viewmodels.SpotifyImportViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playlistPresentationModule = module {
    viewModelOf(::PlaylistsViewModel)
    viewModelOf(::AutoPlaylistViewModel)
    viewModelOf(::SpotifyImportViewModel)
    viewModelOf(::AppleMusicImportViewModel)
}
