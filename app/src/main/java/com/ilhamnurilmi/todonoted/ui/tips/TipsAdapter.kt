package com.ilhamnurilmi.todonoted.ui.tips

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.ilhamnurilmi.todonoted.R
import com.ilhamnurilmi.todonoted.data.model.Tips
import com.ilhamnurilmi.todonoted.data.network.TipsApi
import com.ilhamnurilmi.todonoted.databinding.ItemListTipsBinding

class TipsAdapter : RecyclerView.Adapter<TipsAdapter.TipsViewHolder>() {

    private val items = mutableListOf<Tips>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListTipsData(data : List<Tips>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TipsViewHolder(
        ItemListTipsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) = with(holder) {
        bind(items[position])
    }

    override fun getItemCount() = items.size

    class TipsViewHolder(var binding: ItemListTipsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tips: Tips) = with(binding) {
            Glide.with(this.root)
                .load(TipsApi.getTipsUrl(tips.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(ivTips)
            tvTipsName.text = tips.name

            root.setOnClickListener {

                Snackbar.make(
                    root,
                    tips.name,
                    Snackbar.LENGTH_SHORT,

                    ).show()
            }
        }
    }
}