package com.aurora.music.di

import com.aurora.music.viewmodels.LocalPlaylistViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val localPlaylistPresentationModule = module {
    viewModelOf(::LocalPlaylistViewModel)
}
