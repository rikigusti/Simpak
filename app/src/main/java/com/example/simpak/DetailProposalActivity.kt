package com.example.simpak

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.simpak.databinding.ActivityDetailProposalBinding
import com.example.simpak.model.Proposal

class DetailProposalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProposalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProposalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val proposal = intent.getParcelableExtra<Proposal>("proposal")

        if (proposal != null) {
            // Debug log untuk memastikan data masuk
            Log.d("DetailProposal", "Judul: ${proposal.judul}, Pengaju: ${proposal.pengaju}")

            // Set data ke TextView
            binding.tvJudul.text = proposal.judul.ifEmpty { "Judul tidak tersedia" }
            binding.tvPengaju.text = proposal.pengaju.ifEmpty { "Pengaju tidak diketahui" }
            binding.tvWaktu.text = proposal.waktu.ifEmpty { "Waktu tidak diketahui" }

            val statusText = when (proposal.status.lowercase()) {
                "disetujui" -> "Disetujui"
                "menunggu" -> "Menunggu Persetujuan"
                "ditolak" -> "Ditolak"
                else -> "Status Tidak Diketahui"
            }
            binding.tvStatus.text = statusText

            // Tampilkan gambar menggunakan Glide
            Glide.with(this)
                .load(proposal.fotoUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(binding.ivFotoProposal)
        } else {
            Toast.makeText(this, "Proposal tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
