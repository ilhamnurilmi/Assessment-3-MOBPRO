package com.ilhamnurilmi.todonoted.ui.addtodo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ilhamnurilmi.todonoted.R
import com.ilhamnurilmi.todonoted.data.db.TodoDb
import com.ilhamnurilmi.todonoted.data.model.Todo
import com.ilhamnurilmi.todonoted.databinding.FragmentAddTodoBinding
import com.ilhamnurilmi.todonoted.databinding.FragmentTodoBinding
import com.ilhamnurilmi.todonoted.ui.todo.TodoFragment
import com.ilhamnurilmi.todonoted.ui.todo.TodoFragmentDirections

class AddTodoFragment : Fragment() {

    private lateinit var binding : FragmentAddTodoBinding

    private val viewModel: AddTodoViewModel by lazy {
        val db = TodoDb.getInstance(requireContext())
        val factory = AddTodoViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[AddTodoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddTodo.setOnClickListener {
            addTodo()
            val toast = Toast.makeText(context, "Todo Berhasil ditambahkan", Toast.LENGTH_SHORT)
            toast.show()
            setView()
        }

    }

    private fun addTodo(){

        val todoTitle = binding.edtTodoTitle.text.toString()
        if (TextUtils.isEmpty(todoTitle)) {
            binding.edtTodoTitle.requestFocus()
            Toast.makeText(context, R.string.msg_title_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.addTodo(
            todoTitle,
            false
        )
    }

    private fun setView(){
        binding.apply {
            edtTodoTitle.text?.clear()
        }
    }

}