package com.example.todoapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.TodoDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todoId: Int,
    onNavigateBack: () -> Unit,
    viewModel: TodoDetailViewModel = viewModel()
) {
    val todo by viewModel.getTodo(todoId).collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        todo?.let { TodoDetailContent(it, Modifier.padding(padding)) }
            ?: Text(
                text = "Loading...",
                modifier = Modifier.padding(padding).fillMaxSize(),
                textAlign = TextAlign.Center
            )
    }
}

@Composable
fun TodoDetailContent(todo: Todo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "ID: ${todo.id}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "User ID: ${todo.userId}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Status: ${if (todo.completed) "Completed" else "Not Completed"}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}