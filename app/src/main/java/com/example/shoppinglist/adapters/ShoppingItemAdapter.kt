package com.example.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.datasource.local.entities.ShoppingEntity
import com.example.shoppinglist.data.datasource.local.models.ShoppingModel
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

typealias OnItemChanged = (ShoppingModel) -> Unit
typealias OnItemDeleted = (ShoppingModel) -> Unit

class ShoppingItemAdapter() : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    private var shoppingList = mutableListOf<ShoppingModel>()
    private var onItemChanged: OnItemChanged? = null;
    private var onItemDeleted: OnItemDeleted? = null;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = shoppingList[position]

        holder.itemView.tvName.text = curShoppingItem.name
        holder.itemView.tvAmount.text = "${curShoppingItem.amount}"

        holder.itemView.ivDelete.setOnClickListener {
            onItemDeleted?.invoke(curShoppingItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            curShoppingItem.amount++
            onItemChanged?.invoke(curShoppingItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if (curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                onItemChanged?.invoke(curShoppingItem)
            }
        }
    }

    fun setOnItemChanged(onItemChanged: OnItemChanged) {
        this.onItemChanged = onItemChanged
    }

    fun setOnItemDeleted(onItemDeleted: OnItemDeleted) {
        this.onItemDeleted = onItemDeleted
    }

    fun setItems(shoppingList: List<ShoppingModel>) {
        this.shoppingList = shoppingList.toMutableList()
        notifyDataSetChanged()
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}