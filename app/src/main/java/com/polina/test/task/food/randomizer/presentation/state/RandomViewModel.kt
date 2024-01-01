package com.polina.test.task.food.randomizer.presentation.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polina.test.task.food.randomizer.domain.repository.RandomRepository
import com.polina.test.task.food.randomizer.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val repository: RandomRepository
) : ViewModel() {

    var state by mutableStateOf(RandomUiState())
        private set

    fun handleUiEvent(uiEvent: RandomUiEvent) {
        when (uiEvent) {
            is RandomUiEvent.LoadListData -> loadRandomList()
            is RandomUiEvent.LoadDetailsData -> loadDetails(uiEvent.id)
            is RandomUiEvent.RefreshPage -> refreshData()
        }
    }

    private fun refreshData() {
        loadRandomList()
    }

    private fun loadRandomList() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val response = repository.getRandomItems()) {

                is Resource.Success -> {
                    state = state.copy(
                        title = requireNotNull(response.data?.randomTitle),
                        items = response.data?.randomItems,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        items = null,
                        isLoading = false,
                        error = response.message
                    )
                }
            }
        }
    }

    private fun loadDetails(id: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val response = repository.getItemDetails(id = id)) {

                is Resource.Success -> {
                    state = state.copy(
                        info = requireNotNull(response.data?.info),
                        id = id,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = response.message
                    )
                }
            }
        }
    }
}