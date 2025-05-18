package com.example.todoapp.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.data.TodoRepository

@Composable
fun TodoNavGraph(
    repository: TodoRepository,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") {
            TodoListScreen(
                repository = repository,
                onNavigateToDetail = { todoId ->
                    navController.navigate("todo_detail/$todoId")
                }
            )
        }
        composable("todo_detail/{todoId}") {
            Text("Todo Detail Placeholder")
        }
    }
}