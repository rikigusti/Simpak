package com.example.simpak

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simpak.databinding.ActivityAkunBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AkunActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAkunBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set nama user, nanti bisa ambil dari Firebase/Auth
        binding.tvNamaUser.text = "Nama User"

        // Tombol Riwayat
        binding.btnRiwayat.setOnClickListener {
            val intent = Intent(this, RiwayatActivity::class.java)
            startActivity(intent)
        }

        // Tombol Edit Akun
        binding.btnEditAkun.setOnClickListener {
            binding.btnEditAkun.setOnClickListener {
                val intent = Intent(this, EditAkunActivity::class.java)
                intent.putExtra("nama", binding.tvNamaUser.text.toString())
                intent.putExtra("email", "user@example.com") // Ganti dengan email dari Firebase
                startActivity(intent)
            }

        }

        // Tombol Pengaturan
            binding.btnPengaturan.setOnClickListener {
                startActivity(Intent(this, PeraturanPrivasiActivity::class.java))
            }


        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_account

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_proposal -> {
                    startActivity(Intent(this, ProposalActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_search -> {
                    startActivity(Intent(this, PilihKelasActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_message -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_account -> true

                else -> false
            }
        }
    }
}
