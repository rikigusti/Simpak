package com.example.simpak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simpak.databinding.ActivityPeraturanPrivasiBinding

class PeraturanPrivasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeraturanPrivasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeraturanPrivasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvIsiPeraturan.text = """
            1. Data pengguna akan disimpan dengan aman.
            2. Tidak diperbolehkan menyalahgunakan fitur aplikasi.
            3. Aplikasi ini hanya untuk keperluan internal kampus.
            4. Privasi pengguna dijaga dan tidak dibagikan ke pihak ketiga.
            5. Syarat & ketentuan dapat berubah sewaktu-waktu.
        """.trimIndent()

        binding.btnKembali.setOnClickListener {
            finish()
        }
    }
}
