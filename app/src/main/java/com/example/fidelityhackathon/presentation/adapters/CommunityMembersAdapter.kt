package com.example.fidelityhackathon.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fidelityhackathon.data.models.Members
import com.example.fidelityhackathon.databinding.MemberListBinding

class CommunityMembersAdapter(
    private val dataList: List<Members>
): RecyclerView.Adapter<CommunityMembersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MemberListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = dataList[position]
        holder.setData(member)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val binding: MemberListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(member: Members){
            binding.email.text = member.email
        }
    }
}