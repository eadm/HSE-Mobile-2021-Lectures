package ru.nobird.hse2021.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.nobird.hse2021.sample.databinding.ItemHeaderBinding
import ru.nobird.hse2021.sample.databinding.ItemTextBinding
import ru.nobird.hse2021.sample.model.Item

class ItemsListAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(ItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ItemsAdapter.VIEW_TYPE_HEADER ->
                ItemsAdapter.ViewHolderHeader(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            ItemsAdapter.VIEW_TYPE_TEXT ->
                ItemsAdapter.ViewHolderText(
                    ItemTextBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            else ->
                throw IllegalArgumentException("")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (val item = getItem(position)) {
            is Item.Header ->
                (holder as? ItemsAdapter.ViewHolderHeader)?.onBind(item)

            is Item.WithText ->
                (holder as? ItemsAdapter.ViewHolderText)?.onBind(item)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is Item.Header ->
                ItemsAdapter.VIEW_TYPE_HEADER

            is Item.WithText ->
                ItemsAdapter.VIEW_TYPE_TEXT
        }
}

object ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem is Item.Header && newItem is Item.Header ||
        oldItem is Item.WithText && newItem is Item.WithText

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem == newItem
}