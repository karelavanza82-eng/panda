package com.aurora.music.di

import com.aurora.music.viewmodels.LibraryAlbumsViewModel
import com.aurora.music.viewmodels.LibraryArtistsViewModel
import com.aurora.music.viewmodels.LibraryMixViewModel
import com.aurora.music.viewmodels.LibraryPlaylistsViewModel
import com.aurora.music.viewmodels.LibrarySongsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val librarySectionsPresentationModule = module {
    viewModelOf(::LibrarySongsViewModel)
    viewModelOf(::LibraryArtistsViewModel)
    viewModelOf(::LibraryAlbumsViewModel)
    viewModelOf(::LibraryPlaylistsViewModel)
    viewModelOf(::LibraryMixViewModel)
}
