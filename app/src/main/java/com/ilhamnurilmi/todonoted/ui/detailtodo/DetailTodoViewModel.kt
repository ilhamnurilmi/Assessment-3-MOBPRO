package com.ilhamnurilmi.todonoted.ui.detailtodo

import androidx.lifecycle.ViewModel
import com.ilhamnurilmi.todonoted.data.db.TodoDao

class DetailTodoViewModel (private val db: TodoDao, private val todoId : Long): ViewModel()  {

    val data = db.getOneDataTodo(todoId)

}