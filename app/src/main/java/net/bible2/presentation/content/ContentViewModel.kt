package net.bible2.presentation.content

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.bible2.Bible
import net.bible2.DayOfYear
import net.bible2.DaysInAYear
import net.bible2.Year
import net.bible2.common.Constants
import net.bible2.common.Resource
import net.bible2.domain.model.TheWord
import net.bible2.domain.use_case.GetContentUseCase

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val getContentUseCase: GetContentUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ContentState())
    val state: State<ContentState> = _state

    init {
        savedStateHandle.get<Bible>(Constants.PARAM_BIBLE)?.let { bible ->
            savedStateHandle.get<Year>(Constants.PARAM_YEAR)?.let { year ->
                getTwd(bible, year)
            }
        }
    }

    fun getForDay(dayOfYear: DayOfYear): TheWord? {
        val index = dayOfYear - 1
        if (index < 0 || index >= DaysInAYear) {
            return null
        }
        val items = state.value.theWordFileContent?.items ?: return null
        if (index >= items.size) {
            return null
        }
        return items[index]
    }

    private fun getTwd(bible: Bible, year: Year) {
        getContentUseCase(bible, year).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ContentState(theWordFileContent = result.data)
                }
                is Resource.Error -> {
                    _state.value = ContentState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ContentState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
