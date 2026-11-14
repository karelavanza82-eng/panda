package com.aurora.music.di

import com.aurora.music.viewmodels.ChartsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chartsPresentationModule = module {
    viewModelOf(::ChartsViewModel)
}
