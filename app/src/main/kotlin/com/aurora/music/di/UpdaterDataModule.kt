package com.aurora.music.di

import com.aurora.music.core.updater.ApkVerifier
import com.aurora.music.core.updater.GitHubReleaseSource
import com.aurora.music.core.updater.UpdateInstaller
import com.aurora.music.core.updater.UpdateRepository
import com.aurora.music.core.updater.UpdateStore
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val updaterDataModule = module {
    factoryOf(::ApkVerifier)
    singleOf(::GitHubReleaseSource)
    singleOf(::UpdateStore)
    singleOf(::UpdateInstaller)
    singleOf(::UpdateRepository)
}
