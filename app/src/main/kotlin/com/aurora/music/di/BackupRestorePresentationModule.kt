package com.aurora.music.di

import com.aurora.music.viewmodels.BackupRestoreViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val backupRestorePresentationModule = module {
    viewModelOf(::BackupRestoreViewModel)
}
