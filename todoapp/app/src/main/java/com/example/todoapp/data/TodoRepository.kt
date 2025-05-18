package com.example.todoapp.data

import com.example.todoapp.data.local.TodoDao
import com.example.todoapp.data.local.TodoEntity
import com.example.todoapp.data.remote.TodoApi
import com.example.todoapp.data.remote.TodoDto
import com.example.todoapp.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class TodoRepository(
    private val todoApi: TodoApi,
    private val todoDao: TodoDao
) {
    fun getAllTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos().map { entities ->
            val todos = entities.map { it.toTodo() }
            if (todos.isEmpty()) {
                // Trigger refresh if local data is empty
                refreshTodos()
            }
            todos
        }
    }

    fun getTodoById(id: Int): Flow<Todo?> {
        return todoDao.getTodoById(id).map { it?.toTodo() }
    }

    suspend fun refreshTodos(): Result<Unit> {
        return try {
            val todos = todoApi.getTodos()
            android.util.Log.d("TodoRepository", "Fetched ${todos.size} todos from API")
            todoDao.clearAll()
            todoDao.insertTodos(todos.map { it.toEntity() })
            Result.success(Unit)
        } catch (e: IOException) {
            android.util.Log.e("TodoRepository", "Network error", e)
            Result.failure(e)
        } catch (e: HttpException) {
            android.util.Log.e("TodoRepository", "HTTP error", e)
            Result.failure(e)
        }
    }

    private fun TodoDto.toEntity() = TodoEntity(
        id = id,
        userId = userId,
        title = title,
        completed = completed
    )

    private fun TodoEntity.toTodo() = Todo(
        id = id,
        userId = userId,
        title = title,
        completed = completed
    )
}