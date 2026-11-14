package com.aurora.music.di

import com.aurora.music.viewmodels.YouTubeBrowseViewModel
import com.aurora.music.viewmodels.BrowseViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val browsePresentationModule = module {
    viewModelOf(::YouTubeBrowseViewModel)
    viewModelOf(::BrowseViewModel)
}
