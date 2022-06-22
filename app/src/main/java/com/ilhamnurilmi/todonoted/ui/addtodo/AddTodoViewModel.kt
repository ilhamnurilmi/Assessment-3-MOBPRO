package com.ilhamnurilmi.todonoted.ui.addtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhamnurilmi.todonoted.data.db.TodoDao
import com.ilhamnurilmi.todonoted.data.db.TodoEntity
import com.ilhamnurilmi.todonoted.data.model.Todo
import com.ilhamnurilmi.todonoted.data.model.inputTodo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTodoViewModel (private val db: TodoDao): ViewModel() {

    private val hasilTodo = MutableLiveData<Todo?>()

    val data = db.getDataTodo()

    fun addTodo(title: String, isChecked : Boolean) {
        val dataTodo = TodoEntity(
            title = title,
            isChecked = isChecked
        )
        hasilTodo.value = dataTodo.inputTodo()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataTodo)
            }
        }
    }
}