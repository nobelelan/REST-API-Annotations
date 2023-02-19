package com.example.restapiannotations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.restapiannotations.databinding.RvGetCommentItemBinding
import com.example.restapiannotations.model.Comment

class GetCommentAdapter: RecyclerView.Adapter<GetCommentAdapter.GetCommentViewHolder>() {

    inner class GetCommentViewHolder(val binding: RvGetCommentItemBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<Comment>(){
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetCommentViewHolder {
        return GetCommentViewHolder(RvGetCommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GetCommentViewHolder, position: Int) {
        val comment = differ.currentList[position]
        holder.binding.apply {
            txtPostId.text = comment.postId.toString()
            txtId.text = comment.id.toString()
            txtEmail.text = comment.email
            txtName.text = comment.name
            txtBody.text = comment.body
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}