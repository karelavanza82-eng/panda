package com.aurora.music.di

import com.aurora.music.viewmodels.LyricsAppearanceViewModel
import com.aurora.music.viewmodels.LyricsMenuViewModel
import com.aurora.music.viewmodels.LyricsProviderViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val lyricsPresentationModule = module {
    viewModelOf(::LyricsAppearanceViewModel)
    viewModelOf(::LyricsMenuViewModel)
    viewModelOf(::LyricsProviderViewModel)
}
