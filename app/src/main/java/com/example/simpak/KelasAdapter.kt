package com.example.simpak

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import android.widget.Filter
import android.widget.Filterable
import java.util.*

class KelasAdapter(
    private var daftar: MutableList<Kelas>,
    private val onItemClick: (Kelas) -> Unit
) : RecyclerView.Adapter<KelasAdapter.KelasViewHolder>(), Filterable {

    private var selectedPosition = -1
    private var fullList: List<Kelas> = daftar.toList() // Simpan semua data untuk filtering

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KelasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kelas, parent, false)
        return KelasViewHolder(view)
    }

    override fun onBindViewHolder(holder: KelasViewHolder, @SuppressLint("RecyclerView") position: Int) {
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val filteredList = if (query.isNullOrEmpty()) {
                    fullList
                } else {
                    val keyword = query.toString().lowercase(Locale.getDefault())
                    fullList.filter {
                        it.namaKelas.lowercase(Locale.getDefault()).contains(keyword) ||
                                it.pengisi.lowercase(Locale.getDefault()).contains(keyword)
                    }
                }
                val result = FilterResults()
                result.values = filteredList
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                daftar = (results?.values as? List<Kelas>)?.toMutableList() ?: mutableListOf()
                notifyDataSetChanged()
            }
        }
    }

    fun updateList(newList: List<Kelas>) {
        fullList = newList
        daftar = newList.toMutableList()
        notifyDataSetChanged()
    }
}
