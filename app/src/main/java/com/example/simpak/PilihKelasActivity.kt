package com.example.simpak

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class PilihKelasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: KelasAdapter
    private val daftarKelas = mutableListOf<Kelas>()
    private lateinit var etSearchKelas: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_kelas)

        // Inisialisasi view
        recyclerView = findViewById(R.id.recyclerViewKelas)
        etSearchKelas = findViewById(R.id.etSearchKelas) // Tambahkan EditText di layout
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Data kelas (dummy atau real)
        daftarKelas.addAll(
            listOf(
                Kelas("A101", "Kosong", "-", false, "Gedung A"),
                Kelas("A102", "Dipakai", "Pak Budi", false, "Gedung A"),
                Kelas("B201", "Kosong", "-", false, "Gedung B"),
                Kelas("B202", "Dipakai", "Bu Sari", false, "Gedung B")
            )
        )

        // Setup Adapter
        adapter = KelasAdapter(daftarKelas) { kelas ->
            // Aksi klik
            // val intent = Intent(this, DetailKelasActivity::class.java)
            // intent.putExtra("kelas", kelas)
            // startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Filter teks saat user mengetik
        etSearchKelas.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

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
                R.id.nav_message -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_account -> {
                    startActivity(Intent(this, AkunActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
