package ru.nobird.hse2021.sample.navigation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nobird.hse2021.sample.R
import ru.nobird.hse2021.sample.databinding.ItemTextBinding

class IncrementAdapter(
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<IncrementAdapter.ViewHolder>() {
    var items: List<Int> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    inner class ViewHolder(
        private val viewBinding: ItemTextBinding
    ) : RecyclerView.ViewHolder(viewBinding.text) {
        init {
            viewBinding.root.setOnClickListener { onClick(adapterPosition) }
        }

        fun onBind(item: Int) {
            viewBinding.text.text = viewBinding.root.context.getString(R.string.button_text, item)
        }
    }
}