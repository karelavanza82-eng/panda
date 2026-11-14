package com.aurora.music.di

import com.aurora.music.viewmodels.StatsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val statsPresentationModule = module {
    viewModelOf(::StatsViewModel)
}
