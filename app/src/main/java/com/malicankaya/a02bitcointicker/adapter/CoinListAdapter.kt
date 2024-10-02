package com.malicankaya.a02bitcointicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.malicankaya.a02bitcointicker.databinding.RvCoinListItemBinding
import com.malicankaya.a02bitcointicker.model.CoinModel
import java.util.ArrayList


class CoinListAdapter(var coinList: List<CoinModel>) :
    RecyclerView.Adapter<CoinListAdapter.CoinListVH>() {
    class CoinListVH(val binding: RvCoinListItemBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListVH {
        return CoinListVH(
            RvCoinListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinListVH, position: Int) {
        val item = coinList[position]
        val binding = holder.binding

        binding.tvCoinOrder.text = (position+1).toString()

        Glide.with(holder.itemView.context)
            .load(item.image)
            .apply(
                RequestOptions().centerCrop()
                    .placeholder(CircularProgressDrawable(holder.itemView.context).apply {
                        strokeWidth = 8f
                        centerRadius = 40f
                        start()
                    })
            )
            .into(binding.ivCoinImage)

        binding.tvCoinName.text = item.name
        binding.tvCoinCode.text = item.symbol
        binding.tvCoinPrice.text = item.current_price.toString()
        binding.tvPriceChangePercentage.text =
            item.price_change_percentage_24h.toString()
        binding.tvPriceChangeAmount.text = item.price_change_24h.toString()

        holder.itemView.setOnClickListener {

        }
    }
    fun updateList(coinList : List<CoinModel>){
        this.coinList = coinList
        notifyDataSetChanged();
    }
}