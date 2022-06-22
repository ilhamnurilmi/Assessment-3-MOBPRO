package com.ilhamnurilmi.todonoted.ui.detailtodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ilhamnurilmi.todonoted.data.db.TodoDb
import com.ilhamnurilmi.todonoted.data.model.Todo
import com.ilhamnurilmi.todonoted.databinding.FragmentDetailTodoBinding

class DetailTodoFragment : Fragment() {

    private lateinit var binding : FragmentDetailTodoBinding
    private val args: DetailTodoFragmentArgs by navArgs()

    private val viewModel: DetailTodoViewModel by lazy {
        val db = TodoDb.getInstance(requireContext())
        val factory = DetailTodoViewModelFactory(db.dao, args.todoId)
//        val todoId = args.todoId
        ViewModelProvider(this, factory)[DetailTodoViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner){
            if (it != null) {
                binding.tvTodoTitle.text = it.title
            }
        }
    }

//    private fun getTodoData(todoId : Long) {
////        displayText(todoId)
//    }
//
//    fun displayText(todo : Todo){
//        binding.tvTodoTitle.text = todo.title
//    }
}