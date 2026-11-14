package com.music.innertube

import com.aurora.music.playback.AudioStreamResolver
import com.aurora.music.account.MusicAccountRepository
import com.aurora.music.catalog.MusicCatalogRepository
import com.aurora.music.library.MusicLibraryRemoteDataSource
import com.aurora.music.library.MusicPlaylistRemoteDataSource
import com.music.innertube.account.InnertubeMusicAccountRepository
import com.music.innertube.catalog.InnertubeMusicCatalogRepository
import com.music.innertube.library.InnertubeMusicLibraryDataSource
import com.music.innertube.library.InnertubeMusicPlaylistDataSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun innertubeDataModule(
    webPoTokenProvider: InnertubeWebPoTokenProvider = NoOpInnertubeWebPoTokenProvider,
    streamResolverDecorator: (AudioStreamResolver) -> AudioStreamResolver = { it },
) = module {
    single<InnertubeRepository> { YouTubeRepository() }
    single<InnertubeWebPoTokenProvider> { webPoTokenProvider }
    singleOf(::InnertubeAudioStreamResolver)
    single<AudioStreamResolver> { streamResolverDecorator(get<InnertubeAudioStreamResolver>()) }
    singleOf(::InnertubeMusicCatalogRepository) { bind<MusicCatalogRepository>() }
    singleOf(::InnertubeMusicLibraryDataSource) { bind<MusicLibraryRemoteDataSource>() }
    singleOf(::InnertubeMusicPlaylistDataSource) { bind<MusicPlaylistRemoteDataSource>() }
    singleOf(::InnertubeMusicAccountRepository) { bind<MusicAccountRepository>() }
}
