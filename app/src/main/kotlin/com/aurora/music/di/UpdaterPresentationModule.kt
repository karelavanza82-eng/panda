package com.aurora.music.di

import com.aurora.music.viewmodels.UpdateViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val updaterPresentationModule = module {
    viewModelOf(::UpdateViewModel)
}
