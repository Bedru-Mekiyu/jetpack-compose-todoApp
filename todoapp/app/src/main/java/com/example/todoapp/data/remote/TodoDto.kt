package com.example.todoapp.data.remote

// app/src/main/java/com/example/todoapp/data/remote/TodoDto.kt

import com.google.gson.annotations.SerializedName

data class TodoDto(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)