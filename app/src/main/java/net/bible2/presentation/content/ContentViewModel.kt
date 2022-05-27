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
import net.bible2.common.Constants
import net.bible2.common.Resource
import net.bible2.domain.use_case.GetContentUseCase

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val getContentUseCase: GetContentUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ContentState())
    val state: State<ContentState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_URL)?.let { url ->
            getTwd(url)
        }
    }

    private fun getTwd(url: String) {
        getContentUseCase(url).onEach { result ->
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
