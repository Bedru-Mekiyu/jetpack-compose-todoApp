package com.example.todoapp.data.remote

// app/src/main/java/com/example/todoapp/data/remote/TodoApi.kt


import retrofit2.http.GET

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<TodoDto>
}