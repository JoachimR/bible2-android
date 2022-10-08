package net.bible2.presentation.content

import androidx.compose.runtime.MutableState
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
import net.bible2.Year
import net.bible2.common.Constants
import net.bible2.common.Resource
import net.bible2.domain.use_case.GetContentUseCase

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val getContentUseCase: GetContentUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<ContentState> = mutableStateOf(ContentState.Loading)

    val state: State<ContentState>
        get() = _state

    init {
        savedStateHandle.get<Bible>(Constants.PARAM_BIBLE).let { bible ->
            savedStateHandle.get<Year>(Constants.PARAM_YEAR).let { year ->
                getTwd(bible, year)
            }
        }
    }

    private fun getTwd(bible: Bible?, year: Year?) {
        if (bible == null || year == null) {
            _state.value = ContentState.BibleMissing
            return
        }
        getContentUseCase(bible, year).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ContentState.Loaded(result.data!!)
                }
                is Resource.Error -> {
                    _state.value = ContentState.ErrorLoading(
                        message = result.message
                    )
                }
                is Resource.Loading -> {
                    _state.value = ContentState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }
}
