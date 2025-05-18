package com.example.todoapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.model.Todo
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    repository: TodoRepository,
    onNavigateToDetail: (Int) -> Unit
) {
    val todos = remember { mutableStateOf<List<Todo>>(emptyList()) }
    val isLoading = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf<String?>(null) }

    // Refresh todos from API and collect from Room
    LaunchedEffect(Unit) {
        // Fetch from API
        repository.refreshTodos().onFailure { e ->
            error.value = "Failed to load todos: ${e.message}"
            isLoading.value = false
            android.util.Log.e("TodoListScreen", "Error refreshing todos", e)
        }

        // Collect from Room
        repository.getAllTodos().collectLatest { todoList ->
            todos.value = todoList
            isLoading.value = false
            android.util.Log.d("TodoListScreen", "Loaded ${todoList.size} todos")
        }
    }

    // Fallback to dummy data if todos are empty and not loading
    val displayTodos = when {
        isLoading.value -> listOf() // Show nothing or a loading indicator
        todos.value.isEmpty() -> listOf(
            Todo(1, 1, "Test Todo 1", false),
            Todo(2, 1, "Test Todo 2", true)
        )
        else -> todos.value
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Todo List") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Show error if present
            error.value?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Show loading indicator
            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentSize()
                )
            }

            // Show todos
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(displayTodos) { todo ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onNavigateToDetail(todo.id) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = todo.title,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Checkbox(
                                checked = todo.completed,
                                onCheckedChange = null,
                                enabled = false
                            )
                        }
                    }
                }
            }
        }
    }
}