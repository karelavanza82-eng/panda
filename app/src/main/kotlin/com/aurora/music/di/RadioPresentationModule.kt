package com.aurora.music.di

import com.aurora.music.viewmodels.RadioStationViewModel
import com.aurora.music.viewmodels.RadioViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val radioPresentationModule = module {
    viewModelOf(::RadioStationViewModel)
    viewModelOf(::RadioViewModel)
}
