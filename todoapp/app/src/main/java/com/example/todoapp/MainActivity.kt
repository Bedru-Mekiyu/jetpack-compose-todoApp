package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.local.TodoDatabase
import com.example.todoapp.data.remote.RetrofitClient
import com.example.todoapp.ui.TodoNavGraph
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoDao = TodoDatabase.getDatabase(this).todoDao()
        val repository = TodoRepository(RetrofitClient.todoApi, todoDao).also {
            android.util.Log.d("MainActivity", "Repository initialized")
        }

        setContent {
            TodoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoNavGraph(repository = repository)
                }
            }
        }
    }
}