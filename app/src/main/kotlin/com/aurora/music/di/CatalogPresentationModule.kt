package com.aurora.music.di

import com.aurora.music.viewmodels.AlbumViewModel
import com.aurora.music.viewmodels.AiDjSetViewModel
import com.aurora.music.viewmodels.ArtistAlbumsViewModel
import com.aurora.music.viewmodels.ArtistViewModel
import com.aurora.music.viewmodels.ArtistSongsViewModel
import com.aurora.music.viewmodels.SongInsightViewModel
import com.aurora.music.viewmodels.TopPlaylistViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val catalogPresentationModule = module {
    viewModelOf(::AlbumViewModel)
    viewModelOf(::ArtistViewModel)
    viewModelOf(::ArtistSongsViewModel)
    viewModelOf(::ArtistAlbumsViewModel)
    viewModelOf(::TopPlaylistViewModel)
    viewModelOf(::AiDjSetViewModel)
    viewModelOf(::SongInsightViewModel)
}
