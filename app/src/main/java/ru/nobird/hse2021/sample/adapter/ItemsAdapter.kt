package ru.nobird.hse2021.sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.nobird.hse2021.sample.databinding.ItemHeaderBinding
import ru.nobird.hse2021.sample.databinding.ItemTextBinding
import ru.nobird.hse2021.sample.model.Item

class ItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_HEADER ->
                ViewHolderHeader(ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            VIEW_TYPE_TEXT ->
                ViewHolderText(ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            else ->
                throw IllegalArgumentException("")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is Item.Header ->
                (holder as? ViewHolderHeader)?.onBind(item)

            is Item.WithText ->
                (holder as? ViewHolderText)?.onBind(item)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is Item.Header ->
                VIEW_TYPE_HEADER

            is Item.WithText ->
                VIEW_TYPE_TEXT
        }

    override fun getItemCount(): Int =
        items.size

    class ViewHolderHeader(
        private val viewBinding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.Header) {
            viewBinding.header.text = item.title
        }
    }

    class ViewHolderText(
        private val viewBinding: ItemTextBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.WithText) {
            viewBinding.text.text = item.text
        }
    }

    companion object {
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_TEXT = 2
    }
}