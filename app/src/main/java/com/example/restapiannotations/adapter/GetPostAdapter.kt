package com.example.restapiannotations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.restapiannotations.databinding.RvGetPostItemBinding
import com.example.restapiannotations.model.Post

class GetPostAdapter: RecyclerView.Adapter<GetPostAdapter.GetPostViewHolder>() {

    inner class GetPostViewHolder(val binding: RvGetPostItemBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetPostViewHolder {
        return GetPostViewHolder(RvGetPostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GetPostViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.binding.apply {
            txtId.text = post.id.toString()
            txtTitle.text = post.title
            txtBody.text = post.body
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}