package com.example.todoapp.data.local

// app/src/main/java/com/example/todoapp/data/local/TodoEntity.

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)