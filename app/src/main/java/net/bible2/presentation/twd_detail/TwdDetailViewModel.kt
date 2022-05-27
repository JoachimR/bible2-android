package net.bible2.presentation.twd_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import net.bible2.domain.use_case.get_twd.GetTwdUseCase
import javax.inject.Inject

@HiltViewModel
class TwdDetailViewModel @Inject constructor(
    private val getTwdUseCase: GetTwdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TwdDetailState())
    val state: State<TwdDetailState> = _state

//    init {
//        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { twdId ->
//            getTwd(twdId)
//        }
//    }
//
//    private fun getTwd(twdId: String) {
//        getTwdUseCase(twdId).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = TwdDetailState(twd = result.data)
//                }
//                is Resource.Error -> {
//                    _state.value = TwdDetailState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = TwdDetailState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}