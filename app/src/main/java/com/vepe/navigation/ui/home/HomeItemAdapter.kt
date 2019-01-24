package com.vepe.navigation.ui.home

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.vepe.navigation.R
import com.vepe.navigation.model.Category
import com.vepe.navigation.model.Item
import com.vepe.navigation.presentation.main.MainViewModel


class HomeItemAdapter(private val viewModel: MainViewModel)
    : ListAdapter<String, HomeItemAdapter.ViewHolder>(HomeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Item(position + 1, getItem(position), 12.90))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.item_title)

        fun bind(item: Item) {
            title.text = item.title

            view.setOnClickListener {
                view.findNavController().navigate(R.id.action_detail,
                        setupInputData(item, adapterPosition).arguments)
            }
        }
    }

    private fun setupInputData(item: Item, adapterPosition: Int): NavDirections {
        viewModel.lastSeenItems.add(item)
        // demonstrates sending arguments:
        //  1. Parcelable object through safeArgs
        //  2. enum values through safeArgs
        //     (if adapterPosition == 0 it takes default value specified in layout xml file)
        //  3. array of values through safeArgs
        return HomeFragmentDirections.actionDetail(
                item,
                if (adapterPosition != 0) {
                    Category.values()[(adapterPosition + 1) % Category.values().size]
                } else Category.UNDEFINED,
                viewModel.lastSeenItems.reversed().toTypedArray())
    }

    class HomeItemDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }
}