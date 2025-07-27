package com.example.simpak

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.firestore.FirebaseFirestore

class AddProposalActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_proposal)

        db = FirebaseFirestore.getInstance()

        val etJudul = findViewById<EditText>(R.id.etJudulProposal)
        val etPengaju = findViewById<EditText>(R.id.etPengaju)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitProposal)
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroupKategori)

        btnSubmit.setOnClickListener {
            val judul = etJudul.text.toString().trim()
            val pengaju = etPengaju.text.toString().trim()

            val selectedChipId = chipGroup.checkedChipId
            if (selectedChipId == -1) {
                Toast.makeText(this, "Pilih kategori terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedChip = findViewById<Chip>(selectedChipId)
            val kategori = selectedChip?.text.toString()

            if (judul.isEmpty() || pengaju.isEmpty()) {
                Toast.makeText(this, "Lengkapi semua kolom!", Toast.LENGTH_SHORT).show()
            } else {
                val proposal = hashMapOf(
                    "title" to judul,
                    "applicant" to pengaju,
                    "kategori" to kategori,
                    "status" to "Menunggu",
                    "timestamp" to System.currentTimeMillis()
                )

                db.collection("proposals")
                    .add(proposal)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Proposal berhasil diajukan!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal mengajukan proposal.", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
