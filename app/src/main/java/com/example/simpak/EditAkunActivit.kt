package com.example.simpak

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simpak.databinding.ActivityEditAkunBinding

class EditAkunActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAkunBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Contoh: ambil data nama & email dari intent (kalau dikirim)
        val nama = intent.getStringExtra("nama")
        val email = intent.getStringExtra("email")
        binding.etNama.setText(nama ?: "")
        binding.etEmail.setText(email ?: "")

        binding.btnSimpan.setOnClickListener {
            val namaBaru = binding.etNama.text.toString()
            val emailBaru = binding.etEmail.text.toString()

            // Di sini kamu bisa update ke Firebase Auth atau Firestore
            Toast.makeText(this, "Akun diperbarui: $namaBaru - $emailBaru", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnBatal.setOnClickListener {
            finish()
        }
    }
}
