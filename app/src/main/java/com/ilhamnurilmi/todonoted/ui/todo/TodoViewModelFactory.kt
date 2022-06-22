package com.ilhamnurilmi.todonoted.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilhamnurilmi.todonoted.data.db.TodoDao

class TodoViewModelFactory (
    private val db: TodoDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}