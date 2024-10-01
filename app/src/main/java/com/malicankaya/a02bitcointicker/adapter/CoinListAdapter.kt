package com.malicankaya.a02bitcointicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.malicankaya.a02bitcointicker.databinding.RvCoinListItemBinding
import com.malicankaya.a02bitcointicker.model.CoinModel
import java.util.ArrayList


class CoinListAdapter(val coinList : ArrayList<CoinModel>) : RecyclerView.Adapter<CoinListAdapter.CoinListVH>()
{
    class CoinListVH(val binding : RvCoinListItemBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListVH {
        return CoinListVH(RvCoinListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinListVH, position: Int) {
        val item = coinList[position]
        val binding = holder.binding

        binding.tvCoinOrder.text = position.toString()
        //binding.ivCoinImage
        binding.tvCoinName.text = item.name
        binding.tvCoinCode.text = item.code
        binding.tvCoinPrice.text = item.price.toString()
        binding.tvPriceChangePercentage.text = item.priceChange.toString()

        holder.itemView.setOnClickListener {

        }
    }
}