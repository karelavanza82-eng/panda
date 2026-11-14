package com.aurora.music.di

import com.aurora.music.viewmodels.ArtistItemsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val artistItemsPresentationModule = module {
    viewModelOf(::ArtistItemsViewModel)
}
