package com.aurora.music.di

import com.aurora.music.ui.screens.equalizer.EQViewModel
import com.aurora.music.ui.screens.equalizer.axion.AxionEqViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val equalizerPresentationModule = module {
    viewModelOf(::EQViewModel)
    viewModelOf(::AxionEqViewModel)
}
