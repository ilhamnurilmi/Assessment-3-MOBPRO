package com.ilhamnurilmi.todonoted.data.model

import com.ilhamnurilmi.todonoted.data.db.TodoEntity

fun TodoEntity.inputTodo(): Todo {
    val title = title
    return Todo(title)
}