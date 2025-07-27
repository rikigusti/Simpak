package com.example.simpak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpak.R
import com.example.simpak.databinding.ItemProposalBinding
import com.example.simpak.model.Proposal

class ProposalAdapter(
    private var proposalList: List<Proposal>,
    private val onItemClick: (Proposal) -> Unit
) : RecyclerView.Adapter<ProposalAdapter.ProposalViewHolder>() {

    inner class ProposalViewHolder(val binding: ItemProposalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(proposal: Proposal) {
            binding.tvJudulProposal.text = proposal.judul
            binding.tvNamaPengaju.text = proposal.pengaju
            binding.tvWaktuProposal.text = proposal.waktu

            // Tampilkan status dan ikon status
            binding.tvStatus.text = proposal.status
            val statusDrawable = when (proposal.status.lowercase()) {
                "disetujui" -> R.drawable.ic_check
                "menunggu" -> R.drawable.ic_status_pending
                else -> R.drawable.ic_status_rejected
            }
            binding.ivStatus.setImageResource(statusDrawable)

            // Load image
            Glide.with(binding.root.context)
                .load(proposal.fotoUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.ivProposal)

            binding.root.setOnClickListener { onItemClick(proposal) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProposalViewHolder {
        val binding = ItemProposalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProposalViewHolder(binding)
    }

    override fun getItemCount() = proposalList.size

    override fun onBindViewHolder(holder: ProposalViewHolder, position: Int) {
        holder.bind(proposalList[position])
    }

    fun updateData(newList: List<Proposal>) {
        proposalList = newList
        notifyDataSetChanged()
    }
}
