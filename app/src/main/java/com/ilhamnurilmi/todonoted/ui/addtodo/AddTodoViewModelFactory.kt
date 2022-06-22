package com.ilhamnurilmi.todonoted.ui.addtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilhamnurilmi.todonoted.data.db.TodoDao

class AddTodoViewModelFactory (
    private val db: TodoDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTodoViewModel::class.java)) {
            return AddTodoViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
