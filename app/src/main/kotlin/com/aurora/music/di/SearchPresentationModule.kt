package com.aurora.music.di

import com.aurora.music.ui.screens.search.AppleSearchViewModel
import com.aurora.music.ui.screens.search.suggestions.SuggestionsViewModel
import com.aurora.music.viewmodels.MoodAndGenresViewModel
import com.aurora.music.viewmodels.OnlineSearchViewModel
import com.aurora.music.viewmodels.OnlineSearchSuggestionViewModel
import com.aurora.music.viewmodels.ExploreViewModel
import com.aurora.music.viewmodels.LocalSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchPresentationModule = module {
    viewModelOf(::AppleSearchViewModel)
    viewModelOf(::SuggestionsViewModel)
    viewModelOf(::MoodAndGenresViewModel)
    viewModelOf(::OnlineSearchViewModel)
    viewModelOf(::OnlineSearchSuggestionViewModel)
    viewModelOf(::ExploreViewModel)
    viewModelOf(::LocalSearchViewModel)
}
