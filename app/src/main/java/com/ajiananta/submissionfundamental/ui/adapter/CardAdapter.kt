package com.ajiananta.submissionfundamental.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajiananta.submissionfundamental.data.User
import com.ajiananta.submissionfundamental.databinding.CardPersonBinding
import com.bumptech.glide.Glide

class CardAdapter: RecyclerView.Adapter<CardAdapter.UserViewHolder>() {
    private val list = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users: ArrayList<User>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: CardPersonBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun bind(user: User) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .into(imageViewUser)
                textViewUsername.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = CardPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

}
