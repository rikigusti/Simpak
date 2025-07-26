package com.example.simpak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Tombol "Gaskeun!"
        val btnAjukan = findViewById<Button>(R.id.btnAjukan)
        btnAjukan.setOnClickListener {
            val intent = Intent(this, ProposalActivity::class.java)
            startActivity(intent)
        }

        // Ikon Proposal (jika ada di bagian konten atas)
//        val btnProposal = findViewById<LinearLayout>(R.id.btnProposal)
//        btnProposal?.setOnClickListener {
//            val intent = Intent(this, ProposalActivity::class.java)
//            startActivity(intent)
//        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Sudah di halaman ini
                    true
                }
                R.id.nav_proposal -> {
                    startActivity(Intent(this, ProposalActivity::class.java))
                    true
                }
//                R.id.nav_message -> {
//                    // Misalnya arahkan ke MessageActivity
//                    startActivity(Intent(this, MessageActivity::class.java))
//                    true
//                }
//                R.id.nav_account -> {
//                    // Misalnya arahkan ke AccountActivity
//                    startActivity(Intent(this, AccountActivity::class.java))
//                    true
//                }
                else -> false
            }
        }
    }
}
