package com.example.simpak.adapter

import android.util.Log
import android.view.LayoutInflater
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
            // Debug log data yang diterima
            Log.d("ProposalAdapter", "Bind: judul=${proposal.judul}, pengaju=${proposal.pengaju}, waktu=${proposal.waktu}")

            // Bind teks dengan proteksi ifEmpty
            binding.tvJudulProposal.text = proposal.judul.ifEmpty { "Judul tidak tersedia" }
            binding.tvNamaPengaju.text = proposal.pengaju.ifEmpty { "Pengaju tidak diketahui" }
            binding.tvWaktuProposal.text = proposal.waktu.ifEmpty { "Waktu tidak diketahui" }

            // Bind status dan ikon
            val statusLower = proposal.status.lowercase()
            val statusText = when (statusLower) {
                "disetujui" -> "Disetujui"
                "menunggu" -> "Menunggu"
                "ditolak" -> "Ditolak"
                else -> "Status Tidak Diketahui"
            }

            val statusIcon = when (statusLower) {
                "disetujui" -> R.drawable.ic_check
                "menunggu" -> R.drawable.ic_status_pending
                "ditolak" -> R.drawable.ic_status_rejected
                else -> R.drawable.ic_status_unknown
            }

            binding.tvStatus.text = statusText
            binding.ivStatus.setImageResource(statusIcon)

            // Load image proposal (Glide)
            Glide.with(binding.root.context)
                .load(proposal.fotoUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(binding.ivProposal)

            // Aksi saat diklik
            binding.root.setOnClickListener {
                Log.d("ProposalAdapter", "Item clicked: ${proposal.judul}")
                onItemClick(proposal)
            }
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
