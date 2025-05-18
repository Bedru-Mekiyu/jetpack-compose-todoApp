package com.example.todoapp.viewmodel

// app/src/main/java/com/example/todoapp/viewmodel/TodoDetailViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodoDetailViewModel(
    private val repository: TodoRepository
) : ViewModel() {
    fun getTodo(id: Int): Flow<Todo?> {
        return repository.getTodoById(id)
    }
}