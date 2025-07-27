package com.example.simpak

import android.os.Bundle
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
            binding.tvJudul.text = proposal.judul
            binding.tvPengaju.text = proposal.pengaju
            binding.tvWaktu.text = proposal.waktu
            binding.tvStatus.text = proposal.status

            Glide.with(this)
                .load(proposal.fotoUrl)
                .placeholder(R.drawable.placeholder_image) // pastikan drawable ini ada
                .into(binding.ivFotoProposal)
        } else {
            Toast.makeText(this, "Proposal tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
