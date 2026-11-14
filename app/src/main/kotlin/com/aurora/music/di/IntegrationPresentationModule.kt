package com.aurora.music.di

import com.aurora.music.viewmodels.JamViewModel
import com.aurora.music.viewmodels.ListenTogetherViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val integrationPresentationModule = module {
    viewModelOf(::JamViewModel)
    viewModelOf(::ListenTogetherViewModel)
}
