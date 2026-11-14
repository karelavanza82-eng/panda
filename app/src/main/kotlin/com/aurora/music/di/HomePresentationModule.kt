package com.aurora.music.di

import com.aurora.music.viewmodels.NewReleaseViewModel
import com.aurora.music.viewmodels.HomeViewModel
import com.aurora.music.history.ListenHistoryRepository
import com.aurora.music.history.ListenHistorySettingsRepository
import com.aurora.music.utils.SyncUtils
import com.aurora.music.ui.screens.wrapped.WrappedAudioService
import com.aurora.music.ui.screens.wrapped.WrappedManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::NewReleaseViewModel)
    viewModel {
        val koin = getKoin()
        HomeViewModel(
            context = get(),
            database = get(),
            listenHistoryRepository = ValueProvider { koin.get<ListenHistoryRepository>() },
            listenHistorySettingsRepository = ValueProvider { koin.get<ListenHistorySettingsRepository>() },
            syncUtils = ValueProvider { koin.get<SyncUtils>() },
            wrappedManager = get<WrappedManager>(),
            wrappedAudioService = get<WrappedAudioService>(),
        )
    }
}
