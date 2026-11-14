package com.aurora.music.di

import com.aurora.music.ui.screens.library.LocalMusicViewModel
import com.aurora.music.ui.screens.library.apple.AppleLibraryViewModel
import com.aurora.music.ui.screens.library.apple.AppleLocalSongsViewModel
import com.aurora.music.viewmodels.LocalHomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val localLibraryPresentationModule = module {
    viewModelOf(::LocalMusicViewModel)
    viewModelOf(::LocalHomeViewModel)
    viewModelOf(::AppleLibraryViewModel)
    viewModelOf(::AppleLocalSongsViewModel)
}
