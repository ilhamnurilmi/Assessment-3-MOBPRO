package com.ilhamnurilmi.todonoted.ui.tips

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamnurilmi.todonoted.R
import com.ilhamnurilmi.todonoted.data.network.ApiStatus
import com.ilhamnurilmi.todonoted.databinding.FragmentTipsBinding


class TipsFragment : Fragment() {

    private lateinit var binding : FragmentTipsBinding
    private lateinit var tipsAdapter: TipsAdapter

    private val viewModel: TipsViewModel by lazy {
        ViewModelProvider(this)[TipsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTipsBinding.inflate(layoutInflater, container, false)

        tipsView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

    }

    @SuppressLint("Recycle")
    private fun tipsView() {

        tipsAdapter = TipsAdapter()
        with(binding.rvTipsItems) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tipsAdapter
        }
    }

    private fun observeData(){
        viewModel.getTipsData().observe(viewLifecycleOwner) {
            tipsAdapter.setListTipsData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.errorConnection.visibility = View.VISIBLE
            }
        }
    }

}