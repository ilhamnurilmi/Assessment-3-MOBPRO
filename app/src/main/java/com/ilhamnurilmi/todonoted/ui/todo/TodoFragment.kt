package com.ilhamnurilmi.todonoted.ui.todo

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ilhamnurilmi.todonoted.data.db.TodoDb
import com.ilhamnurilmi.todonoted.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    private lateinit var binding : FragmentTodoBinding
    private lateinit var todoAdapter: TodoAdapter

    private val viewModel: TodoViewModel by lazy {
        val db = TodoDb.getInstance(requireContext())
        val factory = TodoViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[TodoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTodoBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        todoAdapter = TodoAdapter( object : TodoAdapter.OnClickListener {
            override fun onItemClick(id: Long) {}

            override fun goToDetailTodo(todoId: Long) {
                findNavController().navigate(
                    TodoFragmentDirections.actionTodoFragmentToDetailTodoFragment(todoId)
                )
            }

        })


        binding.btnDeleteTodo.setOnClickListener {
            hapusData()
        }

        with(binding.rvTodoItems) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            binding.emptyTextView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            todoAdapter.submitList(it)

        }

    }


    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Konfirmasi penghapusan")
            .setMessage("Yakin ingin menghapus task ini ?")
            .setNegativeButton("Batal") { _, _ ->
            }
            .setPositiveButton("Hapus") { _, _ ->
                viewModel.hapusData()
            }.show()
    }


}