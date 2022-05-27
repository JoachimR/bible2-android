package net.bible2.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.bible2.common.Resource
import net.bible2.domain.use_case.GetListUseCase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ListState())
    val state: State<ListState> = _state

    init {
        downloadList()
    }

    private fun downloadList() {
        getListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ListState(twds = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}