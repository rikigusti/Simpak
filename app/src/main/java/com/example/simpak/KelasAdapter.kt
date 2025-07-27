package com.example.simpak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class KelasAdapter(
    private val daftar: MutableList<Kelas>,
    private val onItemClick: (Kelas) -> Unit
) : RecyclerView.Adapter<KelasAdapter.KelasViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KelasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kelas, parent, false)
        return KelasViewHolder(view)
    }

    override fun onBindViewHolder(holder: KelasViewHolder, position: Int) {
        val kelas = daftar[position]
        holder.tvNama.text = kelas.namaKelas
        holder.tvStatus.text = "${kelas.status} (${kelas.lokasi})"
        holder.tvPengisi.text = "Pengisi: ${kelas.pengisi}"
        holder.checkIcon.visibility = if (position == selectedPosition) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            onItemClick(kelas)
        }
    }

    override fun getItemCount(): Int = daftar.size

    inner class KelasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNamaKelas)
        val tvStatus: TextView = view.findViewById(R.id.tvStatusKelas)
        val tvPengisi: TextView = view.findViewById(R.id.tvPengisi)
        val checkIcon: ImageView = view.findViewById(R.id.ivCheck)
    }
}
