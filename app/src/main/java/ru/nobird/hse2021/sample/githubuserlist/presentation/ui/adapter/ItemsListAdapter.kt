package ru.nobird.hse2021.sample.githubuserlist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nobird.hse2021.sample.databinding.ItemGithubUserBinding
import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser

class UserListAdapter : ListAdapter<GithubUser, RecyclerView.ViewHolder>(GithubUserDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        UserViewHolder(
            ItemGithubUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as? UserViewHolder)?.onBind(item)
    }

    class UserViewHolder(
        private val viewBinding: ItemGithubUserBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(data: GithubUser) {
            viewBinding.login.text = data.login
            viewBinding.homeUrl.text = data.homeUrl

            Glide.with(viewBinding.avatar)
                .load(data.avatarUrl)
                .into(viewBinding.avatar)
        }
    }
}

object GithubUserDiffCallback : DiffUtil.ItemCallback<GithubUser>() {
    override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean =
        oldItem == newItem
}