package com.example.simpak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class PilihKelasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: KelasAdapter
    private val daftarKelas = mutableListOf<Kelas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_kelas)

        recyclerView = findViewById(R.id.recyclerViewKelas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dummy data
        daftarKelas.addAll(
            listOf(
                Kelas("A101", "Kosong", "-", false, "Gedung A"),
                Kelas("A102", "Dipakai", "Pak Budi", false, "Gedung A"),
                Kelas("B201", "Kosong", "-", false, "Gedung B"),
                Kelas("B202", "Dipakai", "Bu Sari", false, "Gedung B")
            )
        )

        adapter = KelasAdapter(daftarKelas) { kelas ->
            // Aksi saat item diklik
        }

        recyclerView.adapter = adapter

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_search

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    true
                }
                R.id.nav_proposal -> {
                    startActivity(Intent(this, ProposalActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_search -> {

                    true
                }
                else -> false
            }
        }
    }
}
