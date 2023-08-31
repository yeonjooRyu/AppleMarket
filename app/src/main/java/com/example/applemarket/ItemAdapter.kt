package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemBinding
import java.text.DecimalFormat

class ItemAdapter(private val mItems: MutableList<SaleItem>) : RecyclerView.Adapter<ItemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null
    var itemLongClick : ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }

        holder.iconImageView.setImageResource(mItems[position].Image)
        holder.title.text = mItems[position].ItemTitle
        holder.address.text = mItems[position].Address

        val price = mItems[position].Price
        holder.price.text = DecimalFormat("#,###").format(price)+"원"

        holder.chat.text = mItems[position].ChatCnt.toString()
        holder.itemLike.text = mItems[position].InterestCnt.toString()

        if(mItems[position].isLike)
            holder.heart.setImageResource(R.drawable.heart)
        else
            holder.heart.setImageResource(R.drawable.heart)
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val title = binding.tvItemTitle
        val address = binding.tvAddress
        val price = binding.tvPrice
        val chat = binding.tvChatCnt
        val itemLike = binding.tvLikeCnt
        val heart = binding.ivLike

    }
}