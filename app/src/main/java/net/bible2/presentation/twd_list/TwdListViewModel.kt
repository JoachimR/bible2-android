package net.bible2.presentation.twd_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.bible2.common.Resource
import net.bible2.domain.use_case.get_twds.GetTwdsUseCase
import javax.inject.Inject

@HiltViewModel
class TwdListViewModel @Inject constructor(
    private val getTwdsUseCase: GetTwdsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TwdListState())
    val state: State<TwdListState> = _state

    init {
        getTwds()
    }

    private fun getTwds() {
        getTwdsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TwdListState(twds = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = TwdListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TwdListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}