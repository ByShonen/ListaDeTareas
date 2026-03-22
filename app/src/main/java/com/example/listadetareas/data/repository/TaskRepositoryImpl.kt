package com.example.listadetareas.data.repository

import com.example.listadetareas.data.TaskDao
import com.example.listadetareas.data.TaskEntity
import com.example.listadetareas.domain.Task
import com.example.listadetareas.domain.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks().map { entities ->
            entities.map { entity ->
                Task(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isDone = entity.isDone
                )
            }
        }
    }

    override suspend fun addTask(task: Task) {
        dao.insertTask(
            TaskEntity(
                title = task.title,
                description = task.description,
                isDone = task.isDone
            )
        )
    }

    override suspend fun updateTask(task: Task) {
        dao.updateTask(
            TaskEntity(
                id = task.id,
                title = task.title,
                description = task.description,
                isDone = task.isDone
            )
        )
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(
            TaskEntity(
                id = task.id,
                title = task.title,
                description = task.description,
                isDone = task.isDone
            )
        )
    }
}
