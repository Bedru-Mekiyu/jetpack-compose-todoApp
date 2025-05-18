// TodoListViewModel.kt
package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val repository: TodoRepository
) : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTodos().collect { todos ->
                _todos.value = todos
            }
        }
        refreshTodos()
    }

    fun refreshTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.refreshTodos().fold(
                onSuccess = {
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _isLoading.value = false
                    _error.value = "Failed to load todos: ${exception.message}"
                }
            )
        }
    }
}