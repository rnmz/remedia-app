package dev.runo.search.ui

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.runo.core.common.WorkStatus
import dev.runo.core.network.title.toTitleType
import dev.runo.search.domain.model.Title
import dev.runo.search.domain.repository.SearchRepository
import dev.runo.search.ui.screen.TitleUiState
import dev.runo.search.ui.screen.TitlesListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    private val _titlesListUiState = MutableStateFlow<TitlesListUiState>(TitlesListUiState())
    val titlesListUiState = _titlesListUiState
        .onStart {
            loadTitles(null, null, null, null, null, null, null, 1)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            TitlesListUiState()
        )

    fun executeIntent(intent: SearchIntent) {
        if (intent is SearchIntent.SearchByFilters) {
            var yearsList: MutableList<Int>? = mutableListOf<Int>()
            when {
                intent.yearFrom != null && intent.yearTo != null -> {
                    for (year in intent.yearFrom..intent.yearTo) {
                        yearsList!!.add(year)
                    }
                }
                intent.yearTo != null -> {
                    val currentYear = LocalDate.now().year
                    val years = intent.yearTo..currentYear
                    for (year in years) {
                        yearsList!!.add(year)
                    }
                }
                intent.yearFrom != null -> {
                    yearsList!!.add(intent.yearFrom)
                }
                else -> {
                    yearsList = null
                }
            }
            loadTitles(
                intent.query,
                intent.genres,
                intent.tags,
                yearsList,
                intent.author,
                intent.titleType,
                intent.orderType,
                intent.page
            )
        }
    }

    private fun loadTitles(
        query: String?,
        genres: List<String>?,
        tags: List<String>?,
        years: List<Int>?,
        author: String?,
        titleType: Int?,
        orderType: Boolean?,
        page: Int
    ) {
        viewModelScope.launch {
            _titlesListUiState.update { it.copy(isLoading = true) }

            val isNull = query == null &&
                    genres == null &&
                    tags == null &&
                    years == null &&
                    author == null &&
                    titleType == null &&
                    orderType == null

            val data = if (isNull) {
                repository.getPopularTitles(page)
            } else {
                repository.searchTitle(
                    query, genres, tags, years, author,
                    titleType?.toTitleType(), orderType, page
                )
            }

            when (data) {
                is WorkStatus.Success -> {
                    _titlesListUiState.update {
                        it.copy(
                            titles = data.data.titles.map { convertToUiModel(it) },
                            allPages = data.data.allPages,
                            isLoading = false
                        )
                    }
                }
                is WorkStatus.Error -> {
                    _titlesListUiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = data.error.toString() // TODO: return string
                        )
                    }
                }
            }
        }
    }

    private fun convertToUiModel(title: Title): TitleUiState {
        return TitleUiState(
            id = title.id,
            image = BitmapFactory.decodeByteArray(title.image, 0, title.image.size - 1),
            name = title.name,
            rating = title.rating
        )
    }

}