package com.aurora.music.di

import com.aurora.music.viewmodels.NetworkBrowserViewModel
import com.aurora.music.viewmodels.NetworkStorageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val networkStoragePresentationModule = module {
    viewModelOf(::NetworkStorageViewModel)
    viewModelOf(::NetworkBrowserViewModel)
}
